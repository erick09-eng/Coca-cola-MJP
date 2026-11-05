package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class TestServicioReposicion {

    private final ServicioReposicion servicio = new ServicioReposicion();

    @Test
    void generaOrdenCuandoInventarioEsMenorOIgualStockMinimo() {
        OrdenReposicion orden = servicio.generarReposicion(4, 10, "PROD-001");
        assertNotNull(orden);
        assertEquals("pendiente", orden.getEstado());
        assertEquals("PROD-001", orden.getProductoId());
    }

    @Test
    void noGeneraOrdenCuandoInventarioEsMayorQueStockMinimo() {
        OrdenReposicion orden = servicio.generarReposicion(15, 10, "PROD-001");
        assertNull(orden);
    }
}
