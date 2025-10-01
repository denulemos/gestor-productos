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
                System.out.println("❌ Opción inválida.");
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
        System.out.println("❌ Producto no encontrado");
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
        System.out.println("❌ Producto no encontrado");
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
                System.out.println("⚠️ Entrada inválida.");
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.println("Nuevo precio:");
                    double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                    producto.setPrecio(nuevoPrecio);
                    System.out.println("💲 Precio actualizado");
                }
                case 2 -> {
                    System.out.println("Nuevo stock:");
                    int nuevoStock = Integer.parseInt(scanner.nextLine());
                    producto.setStock(nuevoStock);
                    System.out.println("📦 Stock actualizado");
                }
                case 3 -> {
                    productos.remove(producto);
                    System.out.println("🗑️ Producto eliminado");
                    return; // salir porque ya no existe
                }
                case 0 -> System.out.println("🔙 Volviendo al menú principal...");
                default -> System.out.println("❌ Opción inválida.");
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

    public void addProducto(String nombre, double precio, int stock) {
        Producto nuevoProducto = new Producto(nombre, precio, stock);
        productos.add(nuevoProducto);
        System.out.println("Producto agregado exitosamente");
    }
}
