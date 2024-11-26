package logic;

import models.ChessTask;
import database.TaskRepository;

public class TaskGenerator {

    // Метод для получения случайной задачи
    public static ChessTask getRandomTask() {
        // Получаем случайную задачу из базы данных
        return TaskRepository.getRandomTask();
    }
}
