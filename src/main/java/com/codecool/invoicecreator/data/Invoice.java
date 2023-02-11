package com.codecool.invoicecreator.data;

import java.time.LocalDate;
import java.util.List;

public class Invoice {

    /**** Invoice ***
    Invoice date: // actual date
    Bill to: // customer data
    Items: // selected products
    Total: // total cost of the selected products
            *** Invoice ****/

    LocalDate date;
    Customer customer;
    List<Product> products;
    int cost;

    public Invoice(LocalDate date, Customer customer, List<Product> products) {
        this.date = date;
        this.customer = customer;
        this.products = products;
        this.cost = calculateCost(products);
    }

    private int calculateCost(List<Product> products){
        int sum = 0;
        for(Product product : products){
            sum += product.getPrice();
        }

        return sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getCost() {
        return cost;
    }
}
