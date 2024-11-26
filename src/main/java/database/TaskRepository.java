package database;

import models.ChessTask;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TaskRepository {
    public static void saveTask(Connection conn, ChessTask task) {
        String query = "INSERT INTO tasks (fen, solution, difficulty) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, task.getFen());
            stmt.setString(2, task.getSolution());
            stmt.setInt(3, task.getDifficulty());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
