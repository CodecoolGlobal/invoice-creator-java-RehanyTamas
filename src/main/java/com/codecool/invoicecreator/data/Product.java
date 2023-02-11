package com.codecool.invoicecreator.data;

public class Product {
    private int id;
    private String name;
    private int categoryId;
    private int price;

    public Product(int id, String name, int categoryId, int price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
