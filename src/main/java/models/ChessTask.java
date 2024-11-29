package models;

import org.json.JSONArray;

public class ChessTask {
    private int id;
    private String fen;
    private JSONArray solution;
    private int difficulty;
    private String gameHistory;

    public ChessTask(int id, String fen, JSONArray solution, int difficulty, String gameHistory) {
        this.id = id;
        this.fen = fen;
        this.solution = solution;
        this.difficulty = difficulty;
        this.gameHistory = gameHistory;

        // Логирование переданной FEN строки
        System.out.println("Создана задача с FEN строкой: " + this.fen);
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public String getFen() {
        return fen;
    }

    public JSONArray getSolution() {
        return solution;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getGameHistory() {
        return gameHistory;
    }

    @Override
    public String toString() {
        return "ChessTask{" +
                "id=" + id +
                ", fen='" + fen + '\'' +
                ", solution=" + solution +
                ", difficulty=" + difficulty +
                ", gameHistory='" + gameHistory + '\'' +
                '}';
    }
}
