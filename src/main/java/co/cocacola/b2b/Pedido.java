package co.cocacola.b2b;

public class Pedido {
    private String pedidoId;
    private String estado;

    public Pedido(String pedidoId, String estado) {
        this.pedidoId = pedidoId;
        this.estado = estado;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public String getEstado() {
        return estado;
    }
}
