package com.codecool.invoicecreator;

import com.codecool.invoicecreator.database.Database;
import com.codecool.invoicecreator.initialize.TableInitializer;
import com.codecool.invoicecreator.initialize.TableStatements;
import com.codecool.invoicecreator.service.CustomerService;
import com.codecool.invoicecreator.service.ProductService;
import com.codecool.invoicecreator.view.InvoiceUI;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Database database = new Database(
                "jdbc:postgresql://localhost:5432/invoice",
                "postgres",
                "postgres");
        Map<String, String> tables = Map.of(
                "category", TableStatements.CATEGORY,
                "customer", TableStatements.CUSTOMER,
                "product", TableStatements.PRODUCT
        );
        TableInitializer tableInitializer = new TableInitializer(database, tables);
        tableInitializer.initialize();

        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();

        Scanner scanner = new Scanner(System.in);
        InvoiceUI invoiceUI = new InvoiceUI(productService, customerService, scanner);
        invoiceUI.run();
    }
}
