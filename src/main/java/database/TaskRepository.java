package database;

import models.ChessTask;
import org.json.JSONArray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaskRepository {

    public static ChessTask getTaskByDifficulty(int difficulty) {
        String query = "SELECT task_id, fen, solution, difficulty, game_history FROM tasks WHERE difficulty = ? ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, difficulty);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("task_id");
                    String fen = rs.getString("fen");
                    String solutionJson = rs.getString("solution");
                    int taskDifficulty = rs.getInt("difficulty");
                    String gameHistory = rs.getString("game_history");

                    System.out.println("Полученная FEN строка из базы данных: " + fen);

                    if (fen == null || fen.trim().isEmpty()) {
                        System.err.println("Ошибка: получена пустая FEN строка из базы данных.");
                        return null;
                    }

                    // Парсинг JSON строки
                    JSONArray solution = new JSONArray(solutionJson);

                    return new ChessTask(id, fen, solution, taskDifficulty, gameHistory);
                } else {
                    System.err.println("Ошибка: не удалось найти задачу в базе данных.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Если задача не найдена
    }
}
