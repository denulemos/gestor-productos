package com.techlab.ordenes;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;

import java.util.ArrayList;
import java.util.Scanner;

public class OrdenService {
    private ArrayList<Orden> ordenes = new ArrayList<>();
    private int secuenciaId = 1;
    private final Scanner scanner = new Scanner(System.in);

    public void listarOrdenes() {
        if (ordenes.isEmpty()) {
            System.out.println("No hay órdenes disponibles.");
            return;
        }
        for (Orden orden : ordenes) {
            System.out.println("Orden ID: " + orden.getId());
            System.out.println("Items:");
            orden.getItems().forEach(item -> System.out.println("- " + item));
            System.out.printf("Total Orden: %.2f%n", orden.getTotal());
            System.out.println("-------------------------");
        }
    }

    public void crearOrdenInteractivo(ProductoService productoService) {
        Orden orden = new Orden(secuenciaId++);
        System.out.println("Creación de orden. Ingrese productos. Escriba FIN para terminar.");

        while (true) {
            productoService.listarProductos();
            System.out.println("ID de producto (o FIN):");
            String idInput = scanner.nextLine().trim();
            if (idInput.equalsIgnoreCase("FIN")) {
                break;
            }

            var optProducto = productoService.obtenerPorId(idInput);
            if (optProducto.isEmpty()) {
                System.out.println("Producto no encontrado.");
                continue;
            }
            Producto producto = optProducto.get();

            System.out.println("Cantidad deseada:");
            String cantInput = scanner.nextLine().trim();
            if (!cantInput.matches("\\d+") || cantInput.startsWith("0")) {
                System.out.println("Cantidad inválida.");
                continue;
            }
            int cantidad = Integer.parseInt(cantInput);

            try {
                validarStock(producto, cantidad);
                // Restar stock
                producto.setStock(producto.getStock() - cantidad);
                orden.addItem(new OrdenItem(producto, cantidad));
                System.out.println("Item agregado.");
            } catch (StockInsuficienteException e) {
                System.out.println(e.getMessage());
            }
        }

        if (orden.getItems().isEmpty()) {
            System.out.println("Orden vacía. No se guardó.");
            return;
        }
        ordenes.add(orden);
        System.out.println("Orden creada con ID: " + orden.getId());
        System.out.printf("Total: %.2f%n", orden.getTotal());
    }

    private void validarStock(Producto producto, int cantidad) throws StockInsuficienteException {
        if (cantidad <= 0) {
            throw new StockInsuficienteException("Cantidad debe ser mayor que cero.");
        }
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente. Disponible: " + producto.getStock());
        }
    }
}
