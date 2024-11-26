package logic;

import models.ChessTask;

public class TaskGenerator {
    public static ChessTask generateTask() {
        String fen = "8/8/8/8/8/8/8/8 w - - 0 1"; // Пример FEN
        String solution = "e4"; // Пример решения
        int difficulty = 1;
        return new ChessTask(fen, solution, difficulty);
    }
}

