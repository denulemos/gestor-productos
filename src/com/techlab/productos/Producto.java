package com.techlab.productos;

public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
