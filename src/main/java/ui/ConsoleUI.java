package ui;

import database.DatabaseConnection;
import database.TaskRepository;
import logic.TaskGenerator;
import models.ChessTask;

import java.sql.Connection;

public class ConsoleUI {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.connect();
        ChessTask task = TaskGenerator.generateTask();
        TaskRepository.saveTask(conn, task);

        System.out.println("Сгенерирована задача: " + task.getFen());
        System.out.println("Решение: " + task.getSolution());
    }
}
