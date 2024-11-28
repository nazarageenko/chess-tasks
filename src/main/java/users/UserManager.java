package users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DatabaseConnection;
import utils.PasswordUtils;
import models.User;

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

    // Обновление рейтинга пользователя
    public static void updateUserRating(String username, int newRating) {
        String query = "UPDATE users SET rating = ? WHERE username = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Устанавливаем параметры запроса
            stmt.setInt(1, newRating);   // Новый рейтинг
            stmt.setString(2, username); // Имя пользователя

            // Выполняем обновление
            stmt.executeUpdate();
            System.out.println("Рейтинг пользователя " + username + " обновлён до " + newRating);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Получение топ-10 пользователей по рейтингу
    public static List<User> getTopUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users ORDER BY rating DESC LIMIT 10";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                int rating = rs.getInt("rating");
                users.add(new User(id, username, rating));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Получение позиции и рейтинга пользователя, если он не в топ-10
    public static User getUserPosition(String username) {
        String query = "SELECT COUNT(*) AS position FROM users WHERE rating > (SELECT rating FROM users WHERE username = ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int position = rs.getInt("position") + 1; // Добавляем 1, чтобы учесть самого пользователя
                int rating = getUserRating(username);
                return new User(position, username, rating);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
