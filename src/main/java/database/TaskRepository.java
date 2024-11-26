package database;

import models.ChessTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaskRepository {

    public static ChessTask getRandomTask() {
        String query = "SELECT task_id, fen, solution, difficulty FROM tasks ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                // Получаем данные из базы
                int id = rs.getInt("task_id");
                String fen = rs.getString("fen");
                String solution = rs.getString("solution");
                int difficulty = rs.getInt("difficulty");

                // Передаем все 4 параметра в конструктор
                return new ChessTask(id, fen, solution, difficulty);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Если задача не найдена
    }
}
