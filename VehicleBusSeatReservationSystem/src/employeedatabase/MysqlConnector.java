package employeedatabase;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
    	 // Database URL, including the database name
        String url = "jdbc:mysql://localhost:3306/companymanager";
        String user = "admin"; // using  MySQL user
        String password = "Pa$$w0rd"; // mySQL user password
        return DriverManager.getConnection(url, user, password);
    }

    // Main method to run the application
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create a new instance of the Login class
                    Login loginWindow = new Login();
                    loginWindow.getFrame().setVisible(true); // Use the getter to show the frame
                } catch (Exception e) {
                	 // Print the stack trace for the exception
                    e.printStackTrace();
                }
            }
        });
    }
}
