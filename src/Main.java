import com.techlab.excepciones.ProductoNoEncontradoException;
import com.techlab.ordenes.OrdenService;
import com.techlab.productos.ProductoService;

import java.util.Scanner;

public class Main {

    private final Scanner in = new Scanner(System.in);
    private final ProductoService productoService = new ProductoService();
    private final OrdenService ordenService = new OrdenService();

    public static void main(String[] args) throws ProductoNoEncontradoException {
        new Main().run();
    }

    public void run() throws ProductoNoEncontradoException {
        String opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            manejarOpcion(opcion);
        } while (!opcion.equals("0"));

        System.out.println("Gracias por tu visita!");
    }

    public void mostrarMenu() {
        System.out.println(" Bienvenid@ al gestor de productos");
        System.out.println("Selecciona una opción:");
        System.out.println("a. Agregar un producto");
        System.out.println("b. Listar productos");
        System.out.println("c. Buscar/Actualizar/Eliminar producto");
        System.out.println("d. Crear Orden");
        System.out.println("e. Listar Ordenes");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    private String leerOpcion() {
        String input = in.nextLine().trim().toLowerCase();
        while (!input.matches("[abcde0]")) {
            System.out.print("Opción inválida. Intenta nuevamente: ");
            input = in.nextLine().trim().toLowerCase();
        }
        return input;
    }

    private void manejarOpcion(String opcion) throws ProductoNoEncontradoException {
        switch (opcion) {
            case "a":
                productoService.agregarProductoOptionHandler();
                break;
            case "b":
                productoService.listarProductos();
                break;
            case "c":
                productoService.buscarProducto();
                break;
            case "d":
                ordenService.crearOrdenInteractivo(productoService);
                break;
            case "e":
                ordenService.listarOrdenes();
                break;
            case "0":
                break;
            default:
                System.out.println("Opción no reconocida");
        }
        if (!opcion.equals("0")) {
            System.out.println("\nPresiona Enter para continuar");
            in.nextLine();
        }
    }
}
