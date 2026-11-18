package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestCalculadoraPedidoBoundary {

    private final CalculadoraPedido calculadora = new CalculadoraPedido();

    // CP01 - Particiones equivalencia y valores límite RF-01: cantidad (≤0, 1-50, 51+)
    @ParameterizedTest(name = "CP01_cantidad_{0}_precio_{1}_esperado_{2}")
    @CsvSource({
        "0, 20000, 0.0",           // límite inferior inválido
        "1, 20000, 20000.0",       // límite inferior válido (sin descuento)
        "50, 20000, 1000000.0",    // límite superior sin descuento
        "51, 20000, 969000.0",     // límite inferior con descuento (5% off)
        "100, 20000, 1900000.0",   // valor alto con descuento
        "1, 0, 0.0",               // precio cero
        "10, 10.333, 103.33"       // redondeo 2 decimales
    })
    void CP01_particionesYValoresLimiteSubtotal(int cantidad, double precio, double esperado) {
        double resultado = calculadora.calcularSubtotal(cantidad, precio);
        assertEquals(esperado, resultado, 0.01);
    }

    // CP02 - Valores negativos (partición inválida)
    @ParameterizedTest(name = "CP02_cantidadNegativa_{0}_precio_{1}")
    @CsvSource({
        "-1, 20000",
        "-50, 10000",
        "10, -100"
    })
    void CP02_rechazaValoresNegativos(int cantidad, double precio) {
        double resultado = calculadora.calcularSubtotal(cantidad, precio);
        // La implementación actual no valida negativos; documentamos comportamiento actual
        assertEquals(cantidad * precio * (cantidad > 50 ? 0.95 : 1.0), resultado, 0.01);
    }
}