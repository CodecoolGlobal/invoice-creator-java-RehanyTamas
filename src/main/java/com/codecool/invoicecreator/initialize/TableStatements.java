package com.codecool.invoicecreator.initialize;

public interface TableStatements {
    String CATEGORY = "CREATE TABLE category (id int, name varchar(20) UNIQUE)";

    String PRODUCT = "CREATE TABLE product (id int,name varchar(20), category_id int , price int)";
    String CUSTOMER = "CREATE TABLE customer (id int,name varchar(20), email varchar(50) UNIQUE,address varchar(50),tax_number int)";

}
