package models;

public class    ChessTask {
    private String fen;
    private String solution;
    private int difficulty;

    public ChessTask(String fen, String solution, int difficulty) {
        this.fen = fen;
        this.solution = solution;
        this.difficulty = difficulty;
    }

    // Геттеры и сеттеры
    public String getFen() { return fen; }
    public String getSolution() { return solution; }
    public int getDifficulty() { return difficulty; }
}

