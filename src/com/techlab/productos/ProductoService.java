package com.techlab.productos;

import java.util.ArrayList;
import java.util.Optional;

public class ProductoService {
    private ArrayList<Producto> productos;

    public Optional<Producto> buscarProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return Optional.of(producto);
            }
        }
        return Optional.empty();
    }

    public Optional<Producto> buscarProductoPorNombre(String nombre) {
        for (Producto producto: productos) {
            if (producto.getNombre() == nombre) {
                return Optional.of(producto);
            }
        }

        return Optional.empty();
    }



    public void listarProductos() {
        System.out.println("--- Lista de productos ---");
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }

    }


}
