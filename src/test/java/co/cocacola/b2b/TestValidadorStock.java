package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

class TestValidadorStock {

    private final ValidadorStock validador = new ValidadorStock();

    @Test
    void lanzaExcepcionCuandoCantidadSuperaStock() {
        assertThrows(StockInsuficienteException.class, () -> validador.validar(60, 20));
    }

    @Test
    void noLanzaExcepcionCuandoCantidadEsMenorOIgualAStock() {
        assertDoesNotThrow(() -> validador.validar(10, 20));
    }
}
