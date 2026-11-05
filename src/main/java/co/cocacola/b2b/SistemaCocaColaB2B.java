package co.cocacola.b2b;

import java.util.Scanner;

public class SistemaCocaColaB2B {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("=== Sistema Coca-Cola B2B ===");
            System.out.println("1. Calcular subtotal con descuento");
            System.out.println("2. Validar stock disponible ");
            System.out.println("3. Generar orden de reposicion ");
            System.out.println("4. Calcular tarifa de envio ");
            System.out.println("5. Actualizar estado del pedido ");
            System.out.println("6. Prueba rapida del programa");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    opcionCalcularSubtotal(scanner);
                    break;
                case 2:
                    opcionValidarStock(scanner);
                    break;
                case 3:
                    opcionGenerarReposicion(scanner);
                    break;
                case 4:
                    opcionCalcularEnvio(scanner);
                    break;
                case 5:
                    opcionActualizarEstado(scanner);
                    break;
                case 6:
                    pruebaRapida();
                    break;
                case 7:
                    System.out.print("¿Quieres una prueba rapida? (s/n): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("s")) {
                        pruebaRapida();
                    }
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

            if (opcion != 7) {
                System.out.println();
            }
        }

        scanner.close();
    }

    private static void opcionCalcularSubtotal(Scanner scanner) {
        CalculadoraPedido calculadora = new CalculadoraPedido();
        System.out.print("Ingrese la cantidad de cajas: ");
        int cantidad = scanner.nextInt();
        System.out.print("Ingrese el precio unitario: ");
        double precio = scanner.nextDouble();
        double resultado = calculadora.calcularSubtotal(cantidad, precio);
        System.out.println("Subtotal: " + resultado);
    }

    private static void opcionValidarStock(Scanner scanner) {
        ValidadorStock validador = new ValidadorStock();
        System.out.print("Ingrese la cantidad solicitada: ");
        int cantidad = scanner.nextInt();
        System.out.print("Ingrese el stock actual: ");
        int stock = scanner.nextInt();
        try {
            validador.validar(cantidad, stock);
            System.out.println("Stock suficiente. Orden valida.");
        } catch (StockInsuficienteException e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
    }

    private static void opcionGenerarReposicion(Scanner scanner) {
        ServicioReposicion servicio = new ServicioReposicion();
        System.out.print("Ingrese el inventario actual: ");
        int inventario = scanner.nextInt();
        System.out.print("Ingrese el stock minimo: ");
        int minimo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el id del producto: ");
        String productoId = scanner.nextLine();
        OrdenReposicion orden = servicio.generarReposicion(inventario, minimo, productoId);
        if (orden != null) {
            System.out.println("Orden generada: " + orden.getProductoId() + " - " + orden.getEstado());
        } else {
            System.out.println("No se genera orden de reposicion.");
        }
    }

    private static void opcionCalcularEnvio(Scanner scanner) {
        CalculadoraEnvio calculadora = new CalculadoraEnvio();
        System.out.print("Ingrese la cantidad de cajas: ");
        int cantidad = scanner.nextInt();
        double resultado = calculadora.calcularEnvio(cantidad);
        System.out.println("Tarifa de envio: " + resultado);
    }

    private static void opcionActualizarEstado(Scanner scanner) {
        GestorPedidos gestor = new GestorPedidos();
        scanner.nextLine();
        System.out.print("Ingrese el id del pedido: ");
        String pedidoId = scanner.nextLine();
        System.out.print("¿El despacho esta confirmado? (s/n): ");
        String respuesta = scanner.nextLine();
        boolean despacho = respuesta.equalsIgnoreCase("s");
        Pedido pedido = gestor.actualizarEstado(pedidoId, despacho);
        System.out.println("Pedido: " + pedido.getPedidoId() + " - Estado: " + pedido.getEstado());
    }

    private static void pruebaRapida() {
        CalculadoraPedido calculadora = new CalculadoraPedido();
        ValidadorStock validador = new ValidadorStock();
        ServicioReposicion servicioReposicion = new ServicioReposicion();
        CalculadoraEnvio calculadoraEnvio = new CalculadoraEnvio();
        GestorPedidos gestorPedidos = new GestorPedidos();

        System.out.println("--- Prueba rapida ---");
        double subtotal = calculadora.calcularSubtotal(51, 20000.0);
        System.out.println("Subtotal con descuento: " + subtotal);

        try {
            validador.validar(60, 20);
        } catch (StockInsuficienteException e) {
            System.out.println("Excepcion: " + e.getMessage());
        }

        OrdenReposicion orden = servicioReposicion.generarReposicion(4, 10, "PROD-001");
        System.out.println("Reposicion: " + orden.getProductoId() + " - " + orden.getEstado());

        double envio = calculadoraEnvio.calcularEnvio(15);
        System.out.println("Envio: " + envio);

        Pedido pedido = gestorPedidos.actualizarEstado("1042", true);
        System.out.println("Pedido: " + pedido.getPedidoId() + " - " + pedido.getEstado());
    }
}
