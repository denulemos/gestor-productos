package com.techlab.ordenes;

import java.util.ArrayList;
import java.util.List;

public class Orden {
    private int id;
    private ArrayList<OrdenItem> items = new ArrayList<>();

    public Orden(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<OrdenItem> getItems() {
        return items;
    }

    public void addItem(OrdenItem item) {
        items.add(item);
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrdenItem::getSubtotal).sum();
    }
}
