package logic;

import models.ChessTask;
import database.TaskRepository;

public class TaskGenerator {

    public static ChessTask getRandomTaskByDifficulty(int difficulty) {
        return TaskRepository.getTaskByDifficulty(difficulty);
    }
}
