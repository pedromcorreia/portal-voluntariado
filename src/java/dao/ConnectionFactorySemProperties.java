package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ciro
 */
public class ConnectionFactorySemProperties {
    private static final String url = "jdbc:mysql://localhost/tcc"; 
    private static final String driverName = "com.mysql.jdbc.Driver";   
    private static final String username = "root";
    private static final String password = "abcd"; 
    private static Connection conn;
    
   public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("Conectado!");
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return conn;
    }

}
