package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TestCalculadoraEnvio {

    private final CalculadoraEnvio calculadora = new CalculadoraEnvio();

    @Test
    void envioBaseCuandoCantidadEsDiezOCuatro() {
        double resultado = calculadora.calcularEnvio(10);
        assertEquals(15000.0, resultado);
    }

    @Test
    void envioConRecargoCuandoCantidadExcedeDiez() {
        double resultado = calculadora.calcularEnvio(15);
        assertEquals(25000.0, resultado);
    }
}
