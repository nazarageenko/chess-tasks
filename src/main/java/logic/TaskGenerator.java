package logic;

import models.ChessTask;
import database.TaskRepository;

public class TaskGenerator {

    public static ChessTask getRandomTask() {
        ChessTask task = TaskRepository.getRandomTask();
        if (task == null) {
            System.err.println("Ошибка: не удалось получить задачу из TaskRepository.");
        } else {
            System.out.println("Успешно получена задача с FEN: " + task.getFen());
        }
        return task;
    }
}
