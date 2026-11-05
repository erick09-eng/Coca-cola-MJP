package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TestGestorPedidos {

    private final GestorPedidos gestor = new GestorPedidos();

    @Test
    void estadoEnRutaCuandoDespachoConfirmado() {
        Pedido pedido = gestor.actualizarEstado("1042", true);
        assertEquals("En Ruta", pedido.getEstado());
    }

    @Test
    void estadoPendienteCuandoDespachoNoConfirmado() {
        Pedido pedido = gestor.actualizarEstado("1042", false);
        assertEquals("Pendiente", pedido.getEstado());
    }
}
