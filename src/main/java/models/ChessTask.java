package models;

import org.json.JSONArray;

public class ChessTask {
    private int id;
    private String fen;
    private JSONArray solution;
    private int difficulty;
    private String gameHistory;
    private char currentPlayer; // Поле для текущего игрока ('w' или 'b')

    public ChessTask(int id, String fen, JSONArray solution, int difficulty, String gameHistory) {
        this.id = id;
        this.fen = fen;
        this.solution = solution;
        this.difficulty = difficulty;
        this.gameHistory = gameHistory;
        this.currentPlayer = extractCurrentPlayer(fen); // Извлечение текущего игрока из FEN строки

        // Логирование переданной FEN строки
        System.out.println("Создана задача с FEN строкой: " + this.fen);
    }

    private char extractCurrentPlayer(String fen) {
        String[] parts = fen.split(" ");
        return parts.length > 1 ? parts[1].charAt(0) : 'w'; // Вторая часть FEN строки указывает на текущего игрока
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

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public String toString() {
        return "ChessTask{" +
                "id=" + id +
                ", fen='" + fen + '\'' +
                ", solution=" + solution +
                ", difficulty=" + difficulty +
                ", gameHistory='" + gameHistory + '\'' +
                ", currentPlayer=" + currentPlayer +
                '}';
    }
}
