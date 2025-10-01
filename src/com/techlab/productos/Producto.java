package com.techlab.productos;

import java.util.UUID;

public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private String id;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id = UUID.randomUUID().toString();
    }

    public String getNombre() {
        return nombre;
    }

    public String toString() {
        return String.format("Id: %d, Nombre: %s, Precio: %.2f, Stock: %d", id, nombre, precio, stock);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

}
