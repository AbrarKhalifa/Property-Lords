package com.mastek.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	  static String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	  static String username = "finalproject";
	  static String password = "sys";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

}
