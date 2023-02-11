package com.codecool.invoicecreator.service;

import com.codecool.invoicecreator.data.Customer;
import com.codecool.invoicecreator.database.Database;

import java.sql.*;
import java.util.Optional;

public class CustomerService {

    private final Database database;

    public CustomerService(Database database) {
        this.database = database;
    }

    public Optional<Customer> findOneByUsername(String name) {
        // Write the select statements here!
        String template = "SELECT * FROM customer WHERE name = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1,name);

            ResultSet resultSet = statement.executeQuery();
            Optional<Customer> customer = Optional.empty();
            if (resultSet.next()) {
                customer = Optional.of(toEntity(resultSet));
            }
            resultSet.close();
            return customer;
        } catch (SQLException e) {
            System.err.println(e);
            return Optional.empty();
        }
    }

    public Optional<Customer> findOneByEmail(String email) {
        // Write the select statements here!
        String template = "SELECT * FROM customer WHERE email = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            Optional<Customer> customer = Optional.empty();
            if (resultSet.next()) {
                customer = Optional.of(toEntity(resultSet));
            }
            resultSet.close();
            return customer;
        } catch (SQLException e) {
            System.err.println(e);
            return Optional.empty();
        }
    }

    private Customer toEntity(ResultSet resultSet) throws SQLException {
        return new Customer(
        //int id, String name, String email, String address, int tax_number
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("address"),
                resultSet.getInt("tax_number")
        );
    }

    public void save(Customer customer) {
        if (findOneByUsername(customer.getName()).isPresent()) {
            System.out.println("Name already taken");
            return;
        }

        // Write the insert statements here!
        String template = "INSERT INTO customer(id, name, email,address,tax_number) VALUES (?,?,?,?,?)";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            prepare(customer, statement);
            statement.executeUpdate();
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void findAll() {
        String template = "SELECT name,email  FROM customer";
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(template)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepare(Customer customer, PreparedStatement statement) throws SQLException {
        statement.setInt(1,customer.getId());
        statement.setString(2, customer.getName());
        statement.setString(3,customer.getEmail());
        statement.setString(4, customer.getAddress());
        statement.setInt(5,customer.getTax_number());
    }
}

