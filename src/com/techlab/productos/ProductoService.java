package com.techlab.productos;

import com.techlab.excepciones.EntradaInvalidaException;
import com.techlab.excepciones.ProductoNoEncontradoException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Optional;

public class ProductoService {
    private ArrayList<Producto> productos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Optional<Producto> buscarProducto() throws ProductoNoEncontradoException {
        this.listarProductos();
        System.out.println("¿Te gustaría seleccionar producto por ID o Nombre?");
        System.out.println("1: Nombre, 2: ID");

        int opcion;
        try {
            opcion = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por defecto se usará búsqueda por ID.");
            opcion = 2;
        }

        Optional<Producto> resultado = switch (opcion) {
            case 1 -> {
                try {
                    yield this.buscarProductoPorNombre();
                } catch (ProductoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                    yield Optional.empty();
                }
            }
            case 2 -> {
                try {
                    yield this.buscarProductoPorId();
                } catch (ProductoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                    yield Optional.empty();
                }
            }
            default -> {
                System.out.println("Opción inválida.");
                yield Optional.empty();
            }
        };

        resultado.ifPresent(this::menuAccionesProducto);

        return resultado;
    }

    private Optional<Producto> buscarProductoPorId() throws ProductoNoEncontradoException{
        System.out.println("Ingresar ID del producto:");
        String id = scanner.nextLine();

        for (Producto producto : productos) {
            if (Objects.equals(producto.getId(), id)) {
                return Optional.of(producto);
            }
        }


        throw new ProductoNoEncontradoException("Producto no encontrado");
    }

    private Optional<Producto> buscarProductoPorNombre() throws ProductoNoEncontradoException {
        System.out.println("Ingresar nombre de producto:");
        String nombre = scanner.nextLine();

        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return Optional.of(producto);
            }
        }
        throw new ProductoNoEncontradoException("Producto no encontrado");
    }

    private void menuAccionesProducto(Producto producto) throws EntradaInvalidaException {
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

            try {
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Nuevo precio:");
                        String precioInput = scanner.nextLine();
                        if (precioInput.isBlank() || !precioInput.matches("\\d+(\\.\\d+)?")) {
                            throw new EntradaInvalidaException("Entrada inválida. Operación cancelada.");
                        }
                        double nuevoPrecio = Double.parseDouble(precioInput);
                        if (nuevoPrecio < 0) {
                            throw new EntradaInvalidaException("Entrada inválida. Operación cancelada.");
                        }
                        producto.setPrecio(nuevoPrecio);
                        System.out.println("Precio actualizado");
                    }
                    case 2 -> {
                        System.out.println("Nuevo stock:");
                        String stockInput = scanner.nextLine();
                        if (stockInput.isBlank() || !stockInput.matches("\\d+") || Integer.parseInt(stockInput) < 0) {
                            throw new EntradaInvalidaException("Entrada inválida. Operación cancelada.");
                        }
                        int nuevoStock = Integer.parseInt(stockInput);
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
            } catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
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
            System.out.println(producto.toString());
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

    public Optional<Producto> obtenerPorId(String id) {
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
