package com.mycompany.hospitalmanagement;

import java.sql.*;

public class DBConnnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String pass = "";
    
    public static Connection getConnection(){
        try{
           Connection conn = DriverManager.getConnection(URL, user, pass);
            System.out.println("Database Connected!");
            return conn;
            
        }catch(SQLException e){
            System.out.println("Database Connecetion Failed!");
            e.printStackTrace();
        }
        return null;
    }
}
