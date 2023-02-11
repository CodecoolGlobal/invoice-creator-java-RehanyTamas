package com.codecool.invoicecreator.view;


import com.codecool.invoicecreator.data.Customer;
import com.codecool.invoicecreator.data.Invoice;
import com.codecool.invoicecreator.data.Product;
import com.codecool.invoicecreator.database.Database;
import com.codecool.invoicecreator.service.ProductService;
import com.codecool.invoicecreator.service.CustomerService;

import java.time.LocalDate;
import java.util.*;

public class InvoiceUI {
    private final ProductService productService;
    private final CustomerService customerService;
    private final Database database;
    private final Scanner scanner;

    public InvoiceUI(ProductService productService, CustomerService customerService, Database database, Scanner scanner) {
        this.productService = productService;
        this.customerService = customerService;
        this.database = database;
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
        System.out.println("3. List categories.");
        System.out.println("4. Exit.");
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
        System.out.print("Id: ");
        String id = scanner.next();
        System.out.print("email: ");
        String email = scanner.next();
        System.out.print("address: ");
        String address = scanner.next();
        System.out.print("tax number: ");
        String taxNumber = scanner.next();
        // Complete save() method
        customerService.save(new Customer(Integer.parseInt(id),name, email, address,Integer.parseInt(taxNumber))
                );
        System.out.println("New customer saved.");
    }

    private void createInvoice() {
        System.out.println("\n*** Select a customer by him/her email address ***");
        Customer customer = getCostumer();

        System.out.println("\n*** Select items from the stock or type 's' to generate the invoice ***");
        List<Product> products = getProducts();

        Invoice invoice = new Invoice(LocalDate.now(),customer,products);
        displayInvoice(invoice);
    }

    public Customer getCostumer(){

        Optional<Customer> customer = Optional.empty();

        while (customer.isEmpty()){
            customerService.findAll();
            System.out.print("\nSelect a customer\n");
            String email = scanner.next();
            customer = customerService.findOneByEmail(email);
        }

        return customer.get();
    }

    public List<Product> getProducts(){
        boolean end = false;

        List<Product> output = new ArrayList<>();

        while(!end){
            productService.findAllByCategories();
            System.out.println("\nSelect Product\n");
            String name = scanner.next();
            if(name.toLowerCase().equals("s")){
                end = true;
            }else{
                Optional<Product> product = productService.findOneByName(name);
                product.ifPresent(output::add);
            }
        }

        return output;
    }

    public void displayInvoice(Invoice invoice){
        System.out.println("*** Invoice ***");
        System.out.println("Invoice date: " + invoice.getDate());
        System.out.println("Bill to: ");
        displayCustomer(invoice.getCustomer());
        System.out.println("Items: ");
        displayProductList(invoice.getProducts());
        System.out.println("Total: " + invoice.getCost());
        System.out.println("*** Invoice ***");

    }

    private void displayCustomer(Customer customer){
        System.out.println("    Name: " + customer.getName());
        System.out.println("    Id: " + customer.getId());
        System.out.println("    Email: " + customer.getEmail());
        System.out.println("    Address: " + customer.getAddress());
        System.out.println("    Tax number: " + customer.getTax_number());
    }


    private void displayProductList(List<Product> products){

        for(Product product : products){
            System.out.println(product.getName());
        }
    }
}
