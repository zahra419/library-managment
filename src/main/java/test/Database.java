package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

   // private static final String URL = "jdbc:mysql://localhost:3306/managment";
    private static final String URL = "jdbc:mysql://localhost:3306/managment?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Optional: load driver (for old versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return conn;
    }
}