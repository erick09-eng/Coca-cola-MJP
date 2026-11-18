package co.cocacola.b2b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestCalculadoraEnvioBoundary {

    private final CalculadoraEnvio calculadora = new CalculadoraEnvio();

    // CP05 - Particiones equivalencia y valores límite RF-04: cantidad de cajas
    // Particiones: ≤0 (inválido), 1..10 (tarifa base), 11..∞ (tarifa base + recargo)
    @ParameterizedTest(name = "CP05_cajas_{0}_esperado_{1}")
    @CsvSource({
        "0, 15000.0",      // límite inferior inválido - tarifa base (comportamiento actual)
        "1, 15000.0",      // límite inferior válido - tarifa base
        "10, 15000.0",     // límite superior sin recargo
        "11, 17000.0",     // límite inferior con recargo (1 caja extra * 2000)
        "15, 25000.0",     // 5 cajas extra * 2000 = 10000 + 15000
        "20, 35000.0",     // 10 cajas extra * 2000 = 20000 + 15000
        "-5, 15000.0"      // negativo - tarifa base (comportamiento actual)
    })
    void CP05_particionesYValoresLimiteEnvio(int cajas, double esperado) {
        double resultado = calculadora.calcularEnvio(cajas);
        assertEquals(esperado, resultado, 0.01);
    }
}