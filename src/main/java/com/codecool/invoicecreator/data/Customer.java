package com.codecool.invoicecreator.data;

public class Customer {
    private  int id;
    private  String name;
    private  String email;
    private String address;
    private  int tax_number;

    public Customer(int id, String name, String email, String address, int tax_number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.tax_number = tax_number;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getTax_number() {
        return tax_number;
    }
}
