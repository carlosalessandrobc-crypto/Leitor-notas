package com.techflow.application.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/techflow"; // Substitua 'techflow' pelo nome do seu banco de dados
    private static final String USER = "root"; // Substitua pelo seu usuário do MySQL
    private static final String PASSWORD = "@#8520@#"; // Substitua pela sua senha do MySQL
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}