package co.cocacola.b2b;

public class CalculadoraPedido {

    public double calcularSubtotal(int cantidad, double precioUnitario) {
        double subtotal = cantidad * precioUnitario;
        if (cantidad > 50) {
            subtotal = subtotal * 0.95;
        }
        return Math.round(subtotal * 100.0) / 100.0;
    }
}
