package com.toby.springoftoby.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost/postgres", "postgres", "1234");
        }
    }
}
