package com.techlab.ordenes;

import java.util.ArrayList;

public class OrdenService {
    private ArrayList<Orden> ordenes = new ArrayList<>();

    public void listarOrdenes() {
        if (ordenes.isEmpty()) {
            System.out.println("No hay órdenes disponibles.");
            return;
        }
        for (Orden orden : ordenes) {
            System.out.println("Orden ID: " + orden.getId());
            System.out.println("Productos en la orden:");
            for (var producto : orden.getProductos()) {
                System.out.println("- " + producto);
            }
            System.out.println("-------------------------");
        }
    }


    public void crearOrden(Orden orden) {
        ordenes.add(orden);
        System.out.println("Orden creada con ID: " + orden.getId());
    }


}
