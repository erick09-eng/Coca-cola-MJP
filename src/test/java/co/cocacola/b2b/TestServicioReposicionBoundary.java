package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestServicioReposicionBoundary {

    private final ServicioReposicion servicio = new ServicioReposicion();

    // CP04 - Particiones equivalencia y valores límite RF-03: inventario vs stock mínimo
    @ParameterizedTest(name = "CP04_inventario_{0}_minimo_{1}_generaOrden_{2}")
    @CsvSource({
        "0, 10, true",
        "1, 10, true",
        "10, 10, true",
        "11, 10, false",
        "100, 10, false",
        "-5, 10, true"
    })
    void CP04_particionesYValoresLimiteReposicion(int inventario, int minimo, boolean esperaOrden) {
        OrdenReposicion orden = servicio.generarReposicion(inventario, minimo, "PROD-001");
        if (esperaOrden) {
            assertNotNull(orden);
            assertEquals("PROD-001", orden.getProductoId());
            assertEquals("pendiente", orden.getEstado());
        } else {
            assertNull(orden);
        }
    }
}
