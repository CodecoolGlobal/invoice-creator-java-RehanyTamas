package com.codecool.invoicecreator.view;


import com.codecool.invoicecreator.service.ProductService;
import com.codecool.invoicecreator.service.CustomerService;

import java.util.*;

public class InvoiceUI {
    private final ProductService productService;
    private final CustomerService customerService;
    private final Scanner scanner;

    public InvoiceUI(ProductService productService, CustomerService customerService, Scanner scanner) {
        this.productService = productService;
        this.customerService = customerService;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            displayMainMenu();
            handleChoice();
        }
    }

    private void displayMainMenu() {
        System.out.println("\n----- Invoice creator -----");
        System.out.println("\nAvailable products by categories: ");
        // Complete findAllByCategories() to summarize products by categories
        productService.findAllByCategories();

        System.out.println("\nOptions: ");
        System.out.println("1. Register new customer.");
        System.out.println("2. Create an invoice for a customer.");
        System.out.println("3. Exit.");
    }

    private void handleChoice() {
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> addNewCustomer();
            case 2 -> createInvoice();
            case 3 -> System.exit(0);
        }
    }

    private void addNewCustomer() {
        System.out.println("\n*** Register new customer ***");
        System.out.print("Username: ");
        String name = scanner.next();
        System.out.print("email: ");
        String email = scanner.next();
        System.out.print("address: ");
        String address = scanner.next();
        System.out.print("tax number: ");
        String taxNumber = scanner.next();
        // Complete save() method
        customerService.save(name, email, address, taxNumber);
        System.out.println("New customer saved.");
    }

    private void createInvoice() {
        System.out.println("\n*** Select a customer by him/her email address ***");
        // Print the available customers to the console and select one.

        System.out.println("\n*** Select items from the stock or press 's' to generate the invoice ***");
        // Print the available products to the console, select some, and generate invoice
    }
}
