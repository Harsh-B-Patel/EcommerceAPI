package harsh.projects.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws ClassNotFoundException {
        String DB_URL = "jdbc:mysql://localhost:3306/ecommerce";
        String DB_USER = "root";
        String DB_PASSWORD = "2048";
        //Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
