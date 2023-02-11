package com.codecool.invoicecreator.service;


import com.codecool.invoicecreator.data.Customer;
import com.codecool.invoicecreator.data.Product;
import com.codecool.invoicecreator.database.Database;

import java.sql.*;
import java.util.Optional;

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

    public Optional<Product> findOneByName(String name) {
        // Write the select statements here!
        String template = "SELECT * FROM product WHERE name = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            Optional<Product> product = Optional.empty();
            if (resultSet.next()) {
                product = Optional.of(toEntity(resultSet));
            }
            resultSet.close();
            return product;
        } catch (SQLException e) {
            System.err.println(e);
            return Optional.empty();
        }
    }

    private Product toEntity(ResultSet resultSet) throws SQLException {
        return new Product(
                //int id, String name, String email, String address, int tax_number
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("category_id"),
                resultSet.getInt("price")
        );
    }
}
