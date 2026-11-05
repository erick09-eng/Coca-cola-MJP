package co.cocacola.b2b;

public class ServicioReposicion {

    public OrdenReposicion generarReposicion(int inventarioActual, int stockMinimo, String productoId) {
        if (inventarioActual <= stockMinimo) {
            return new OrdenReposicion(productoId, "pendiente");
        }
        return null;
    }
}
