package com.codecool.invoicecreator.service;


import com.codecool.invoicecreator.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductService {

    Database database;

    public ProductService(Database database) {
        this.database = database;
    }

    public void findAllByCategories() {
        String template = "SELECT category.name AS name, COUNT(*) AS numberOfproducts FROM category LEFT JOIN product ON category.id =product.category_id GROUP BY category.name";
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(template)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " : " + resultSet.getInt("numberOfproducts"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
