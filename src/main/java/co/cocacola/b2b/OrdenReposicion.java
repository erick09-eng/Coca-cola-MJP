package co.cocacola.b2b;

public class OrdenReposicion {
    private String productoId;
    private String estado;

    public OrdenReposicion(String productoId, String estado) {
        this.productoId = productoId;
        this.estado = estado;
    }

    public String getProductoId() {
        return productoId;
    }

    public String getEstado() {
        return estado;
    }
}
