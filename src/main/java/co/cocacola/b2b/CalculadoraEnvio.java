package co.cocacola.b2b;

public class CalculadoraEnvio {

    public double calcularEnvio(int cantidadCajas) {
        double tarifaBase = 15000.0;
        if (cantidadCajas > 10) {
            int adicionales = cantidadCajas - 10;
            return tarifaBase + (adicionales * 2000.0);
        }
        return tarifaBase;
    }
}
