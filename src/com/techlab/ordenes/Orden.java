package com.techlab.ordenes;

import com.techlab.productos.Producto;

import java.util.ArrayList;

public class Orden {
    private int id;
    private ArrayList<Producto> productos;

    public Orden(int id, ArrayList<Producto> productos) {
        this.id = id;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

}
