package com.example.mutuelle.DAO;
import java.sql.*;

public class ConnectionClass {
    static java.sql.Connection connection = null;
    static String url = "jdbc:mysql://localhost:3306/";
    static String dbName = "mutuelle";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String userName = "root";
    static String password = "1234";

    public java.sql.Connection getConnection() {


        try {

            Class.forName(driver);
            connection = (java.sql.Connection) DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connection established succesfully!");
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("getConnection : Error" + e.getMessage());
            return null;

        }


    }
}