package co.cocacola.b2b;

public class ValidadorStock {

    public void validar(int cantidad, int stockActual) throws StockInsuficienteException {
        if (cantidad > stockActual) {
            throw new StockInsuficienteException("Stock insuficiente: se solicitaron " + cantidad + " y hay " + stockActual);
        }
    }
}
