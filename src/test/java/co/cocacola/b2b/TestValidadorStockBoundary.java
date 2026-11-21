package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestValidadorStockBoundary {

    private final ValidadorStock validador = new ValidadorStock();

    // CP03 - Particiones equivalencia y valores límite RF-02: cantidad vs stock
    @ParameterizedTest(name = "CP03_cantidad_{0}_stock_{1}_esperadoExcepcion_{2}")
    @CsvSource({
        "0, 10, false",
        "1, 10, false",
        "10, 10, false",
        "11, 10, true",
        "100, 10, true",
        "-5, 10, false"
    })
    void CP03_particionesYValoresLimiteStock(int cantidad, int stock, boolean esperaExcepcion) {
        if (esperaExcepcion) {
            assertThrows(StockInsuficienteException.class, () -> validador.validar(cantidad, stock));
        } else {
            assertDoesNotThrow(() -> validador.validar(cantidad, stock));
        }
    }
}
