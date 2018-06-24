package database;

import java.sql.*;

public class ConnectionManager {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog", "root", "rootpw");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
