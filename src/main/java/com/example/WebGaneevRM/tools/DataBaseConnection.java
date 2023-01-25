package com.example.WebGaneevRM.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке драйвера - " + e.getMessage());
        }
        String url = "jdbc:postgresql://localhost:5432/board_games";
        String user = "postgres";
        String pass = "1234";
        return DriverManager.getConnection(url, user, pass);
    }
}
