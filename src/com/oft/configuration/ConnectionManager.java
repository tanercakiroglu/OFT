package com.oft.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";    
    private static String driverName = "oracle.jdbc.driver.OracleDriver";   
    private final static String usernsme = "taner";   
    private final static String pasword = "taner1";
    private static Connection con;
  
    
    protected static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
            	con = DriverManager.getConnection(url, usernsme, pasword);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }


}
