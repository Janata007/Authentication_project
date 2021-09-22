package com.example.security;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.*;


@SpringBootApplication
@Configuration
//@ComponentScan({"com.example.security"})
public class SecurityApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(SecurityApplication.class, args);

/*
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ib_security", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from oceni");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("predmet"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }


}
