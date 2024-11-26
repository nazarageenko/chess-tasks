package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DatabaseConnection;
import utils.PasswordUtils;

public class UserManager {

    // Регистрация пользователя
    public static boolean registerUser(String username, String password) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, PasswordUtils.hashPassword(password));
                stmt.executeUpdate();
                return true;  // Успешная регистрация
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Ошибка, возможно, уже существует пользователь с таким логином
        }
    }

    // Аутентификация пользователя
    public static boolean authenticateUser(String username, String password) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT password_hash FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String storedPasswordHash = rs.getString("password_hash");
                    return PasswordUtils.checkPassword(password, storedPasswordHash);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Пользователь не найден или пароль неверный
    }

    // Получение рейтинга пользователя
    public static int getUserRating(String username) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT rating FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("rating");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Возвращаем -1, если пользователь не найден
    }
}

