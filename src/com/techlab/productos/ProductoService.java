package com.techlab.productos;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Optional;

public class ProductoService {
    private ArrayList<Producto> productos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Optional<Producto> buscarProducto() {
        System.out.println("¿Te gustaría buscar producto por ID o Nombre?");
        System.out.println("1: Nombre, 2: ID");

        int opcion;
        try {
            opcion = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada inválida. Por defecto se usará búsqueda por ID.");
            opcion = 2;
        }

        Optional<Producto> resultado = switch (opcion) {
            case 1 -> this.buscarProductoPorNombre();
            case 2 -> this.buscarProductoPorId();
            default -> {
                System.out.println("Opción inválida.");
                yield Optional.empty();
            }
        };

        resultado.ifPresent(this::menuAccionesProducto);

        return resultado;
    }

    private Optional<Producto> buscarProductoPorId() {
        System.out.println("Ingresar ID del producto:");
        String id = scanner.nextLine();

        for (Producto producto : productos) {
            if (Objects.equals(producto.getId(), id)) {
                return Optional.of(producto);
            }
        }
        System.out.println("Producto no encontrado");
        return Optional.empty();
    }

    private Optional<Producto> buscarProductoPorNombre() {
        System.out.println("Ingresar nombre de producto:");
        String nombre = scanner.nextLine();

        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) { // usar equalsIgnoreCase
                return Optional.of(producto);
            }
        }
        System.out.println("Producto no encontrado");
        return Optional.empty();
    }

    private void menuAccionesProducto(Producto producto) {
        int opcion;
        do {
            System.out.println("\n✅ Producto encontrado: " + producto);
            System.out.println("¿Qué deseas hacer?");
            System.out.println("1: Modificar precio");
            System.out.println("2: Modificar stock");
            System.out.println("3: Eliminar producto");
            System.out.println("0: Volver al menú principal");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.println("Nuevo precio:");
                    double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                    producto.setPrecio(nuevoPrecio);
                    System.out.println("Precio actualizado");
                }
                case 2 -> {
                    System.out.println("Nuevo stock:");
                    int nuevoStock = Integer.parseInt(scanner.nextLine());
                    producto.setStock(nuevoStock);
                    System.out.println("Stock actualizado");
                }
                case 3 -> {
                    productos.remove(producto);
                    System.out.println("Producto eliminado");
                    return;
                }
                case 0 -> System.out.println("Volviendo al menú");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("--- Lista de Productos vacía ---");
            return;
        }
        System.out.println("--- Lista de productos ---");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public void agregarProductoOptionHandler() {
        int CODIGO_SALIDA = 00;
        System.out.println("Agregar nuevo producto, ingresar doble 00 para cancelar");
        System.out.println("Nombre del producto:");
        String nombre = scanner.nextLine();
        while (nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío. Intentar de nuevo.");
            nombre = scanner.nextLine();
        }
        if (nombre.equals(Integer.toString(CODIGO_SALIDA))) {
            System.out.println("Operación cancelada");
            return;
        }
        System.out.println("Precio del producto:");
        String precioInput = scanner.nextLine();
        while (precioInput.isBlank() || !precioInput.matches("\\d+(\\.\\d+)?") || Double.parseDouble(precioInput) < 0) {
            System.out.println("El precio no puede estar vacío o ser inválido. Intentar de nuevo.");
            precioInput = scanner.nextLine();
        }
        if (precioInput.equals(Integer.toString(CODIGO_SALIDA))) {
            System.out.println("Operación cancelada");
            return;
        }
        double precio = Double.parseDouble(precioInput);

        System.out.println("Stock del producto:");
        String stockInput = scanner.nextLine();
        while (stockInput.isBlank() || !stockInput.matches("\\d+") || Integer.parseInt(stockInput) < 0) {
            System.out.println("El stock no puede estar vacío o ser inválido. Intentar de nuevo.");
            stockInput = scanner.nextLine();
        }
        if (stockInput.equals(Integer.toString(CODIGO_SALIDA))) {
            System.out.println("Operación cancelada");
            return;
        }
        int stock = Integer.parseInt(stockInput);

        this.agregarProducto(nombre, precio, stock);
    }

    private void agregarProducto(String nombre, double precio, int stock) {
        Producto nuevoProducto = new Producto(nombre, precio, stock);
        productos.add(nuevoProducto);
        System.out.println("Producto agregado exitosamente");
    }
}
