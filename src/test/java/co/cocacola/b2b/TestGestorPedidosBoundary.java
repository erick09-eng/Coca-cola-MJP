package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestGestorPedidosBoundary {

    private final GestorPedidos gestor = new GestorPedidos();

    // CP06 - Particiones equivalencia y valores límite RF-05: despachoConfirmado (boolean)
    // Solo 2 particiones: true -> "En Ruta", false -> "Pendiente"
    // Valores límite: ID vacío, ID normal, ID largo
    @ParameterizedTest(name = "CP06_pedidoId_{0}_despacho_{1}_esperado_{2}")
    @CsvSource({
        "'', true, 'En Ruta'",
        "'1042', true, 'En Ruta'",
        "'PEDIDO-12345-LARGO', true, 'En Ruta'",
        "'', false, 'Pendiente'",
        "'1042', false, 'Pendiente'",
        "'PEDIDO-12345-LARGO', false, 'Pendiente'"
    })
    void CP06_particionesYValoresLimiteEstadoPedido(String pedidoId, boolean despacho, String esperado) {
        Pedido pedido = gestor.actualizarEstado(pedidoId, despacho);
        assertNotNull(pedido);
        assertEquals(pedidoId, pedido.getPedidoId());
        assertEquals(esperado, pedido.getEstado());
    }
}