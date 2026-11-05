package co.cocacola.b2b;

public class GestorPedidos {

    public Pedido actualizarEstado(String pedidoId, boolean despachoConfirmado) {
        String estado = despachoConfirmado ? "En Ruta" : "Pendiente";
        return new Pedido(pedidoId, estado);
    }
}
