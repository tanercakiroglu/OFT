package com.oft.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
	
//	private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";    
//  private static String driverName = "oracle.jdbc.driver.OracleDriver";  
	private static String url = "jdbc:mysql://127.0.0.1:3307/oft";    
    private static String driverName = "com.mysql.jdbc.Driver";   
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
    
    protected static int selectUser(String token){
    	PreparedStatement  pstmt = null;
    	int size=0;
    	 try {
			pstmt = getConnection().prepareStatement("SELECT  * FROM oft_user where token=?");
			pstmt.setString(1, token);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				size++;
			}
            return size;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

         
    }


}
