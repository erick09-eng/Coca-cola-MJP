package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class TestIntegracionFlujoPedido {

    private final CalculadoraPedido calculadoraPedido = new CalculadoraPedido();
    private final ValidadorStock validadorStock = new ValidadorStock();
    private final ServicioReposicion servicioReposicion = new ServicioReposicion();
    private final CalculadoraEnvio calculadoraEnvio = new CalculadoraEnvio();
    private final GestorPedidos gestorPedidos = new GestorPedidos();

    // CP07 - Test de integración: flujo completo de un pedido B2B
    // Usa 5 clases reales trabajando juntas: CalculadoraPedido + ValidadorStock + 
    // ServicioReposicion + CalculadoraEnvio + GestorPedidos
    @Test
    void CP07_flujoCompletoPedidoConStockSuficiente() {
        // Datos de entrada
        int cantidad = 30;
        double precioUnitario = 25000.0;
        int stockActual = 50;
        int inventarioActual = 20;
        int stockMinimo = 10;
        String productoId = "PROD-COKE-500";
        String pedidoId = "PED-2026-001";
        boolean despachoConfirmado = true;

        // 1. Calcular subtotal (RF-01)
        double subtotal = calculadoraPedido.calcularSubtotal(cantidad, precioUnitario);
        assertEquals(750000.0, subtotal); // 30 * 25000 = 750000 (sin descuento, ≤50)

        // 2. Validar stock (RF-02) - no debe lanzar excepción
        try {
            validadorStock.validar(cantidad, stockActual); // 30 ≤ 50, OK
        } catch (StockInsuficienteException e) {
            throw new AssertionError("No debería lanzar excepción", e);
        }

        // 3. Verificar reposición (RF-03) - inventario 20 > mínimo 10, no genera
        OrdenReposicion orden = servicioReposicion.generarReposicion(inventarioActual, stockMinimo, productoId);
        assertNull(orden);

        // 4. Calcular envío (RF-04) - 30 cajas > 10, tiene recargo
        double envio = calculadoraEnvio.calcularEnvio(cantidad);
        assertEquals(55000.0, envio); // 15000 + (20 * 2000) = 55000

        // 5. Actualizar estado pedido (RF-05) - despacho confirmado
        Pedido pedido = gestorPedidos.actualizarEstado(pedidoId, despachoConfirmado);
        assertNotNull(pedido);
        assertEquals(pedidoId, pedido.getPedidoId());
        assertEquals("En Ruta", pedido.getEstado());

        // Verificación final: total = subtotal + envío
        double total = subtotal + envio;
        assertEquals(805000.0, total);
    }

    @Test
    void CP08_flujoCompletoPedidoConStockInsuficienteGeneraReposicion() {
        int cantidad = 60;
        double precioUnitario = 20000.0;
        int stockActual = 20;      // Stock insuficiente
        int inventarioActual = 5;  // Bajo mínimo
        int stockMinimo = 10;
        String productoId = "PROD-COKE-1L";
        String pedidoId = "PED-2026-002";

        // 1. Subtotal con descuento (>50)
        double subtotal = calculadoraPedido.calcularSubtotal(cantidad, precioUnitario);
        assertEquals(1140000.0, subtotal); // 60*20000*0.95 = 1140000

        // 2. Validar stock - DEBE lanzar excepción
        assertThrows(StockInsuficienteException.class, () -> validadorStock.validar(cantidad, stockActual));

        // 3. Reposición - inventario 5 ≤ mínimo 10, genera orden
        OrdenReposicion orden = servicioReposicion.generarReposicion(inventarioActual, stockMinimo, productoId);
        assertNotNull(orden);
        assertEquals(productoId, orden.getProductoId());
        assertEquals("pendiente", orden.getEstado());

        // 4. Envío - 60 cajas
        double envio = calculadoraEnvio.calcularEnvio(cantidad);
        assertEquals(115000.0, envio); // 15000 + (50 * 2000) = 115000

        // 5. Pedido sin despacho
        Pedido pedido = gestorPedidos.actualizarEstado(pedidoId, false);
        assertEquals("Pendiente", pedido.getEstado());
    }

    @Test
    void CP09_flujoPedidoExactamenteEnLimites() {
        // Cantidad items = 50 (límite sin descuento), cajas envío = 10 (límite sin recargo)
        int cantidadItems = 50;
        int cajasEnvio = 10;
        double precio = 10000.0;
        int stock = 50;
        int inventario = 10;
        int minimo = 10;

        double subtotal = calculadoraPedido.calcularSubtotal(cantidadItems, precio);
        assertEquals(500000.0, subtotal); // 50 * 10000 = 500000 (sin descuento)

        try {
            validadorStock.validar(cantidadItems, stock); // 50 ≤ 50, OK
        } catch (StockInsuficienteException e) {
            throw new AssertionError("No debería lanzar excepción", e);
        }

        OrdenReposicion orden = servicioReposicion.generarReposicion(inventario, minimo, "PROD-LIM");
        assertNotNull(orden); // 10 ≤ 10, genera

        double envio = calculadoraEnvio.calcularEnvio(cajasEnvio);
        assertEquals(15000.0, envio); // 10 cajas = tarifa base

        Pedido pedido = gestorPedidos.actualizarEstado("PED-LIMITE", true);
        assertEquals("En Ruta", pedido.getEstado());
    }
}