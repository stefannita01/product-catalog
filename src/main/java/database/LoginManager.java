package database;

import entities.Admin;
import entities.IUser;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginManager {
    public static IUser login(String username, String password) {
        IUser user = null;

        Connection conn = ConnectionManager.getConnection();

        PreparedStatement preparedStatement = null;
        String sql = "select * from users where binary username = ? "
                + "and binary pwd = ? ";

        try {
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int admin = rs.getInt("is_admin");

                if (admin == 1) {
                    user = new Admin(username);
                } else {
                    user = new User(username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
