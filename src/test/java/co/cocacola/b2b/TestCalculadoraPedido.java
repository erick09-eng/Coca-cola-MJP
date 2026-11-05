package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TestCalculadoraPedido {

    private final CalculadoraPedido calculadora = new CalculadoraPedido();

    @Test
    void subtotalSinDescuentoCuandoCantidadEsIgualACincuenta() {
        double resultado = calculadora.calcularSubtotal(50, 20000.0);
        assertEquals(1000000.0, resultado);
    }

    @Test
    void subtotalConDescuentoCuandoCantidadEsMayorACincuenta() {
        double resultado = calculadora.calcularSubtotal(51, 20000.0);
        assertEquals(969000.0, resultado);
    }

    @Test
    void subtotalRedondeadoADosDecimales() {
        double resultado = calculadora.calcularSubtotal(3, 10.333);
        assertEquals(31.0, resultado);
    }
}
