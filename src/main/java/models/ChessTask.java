package models;

public class ChessTask {
    private int id;
    private String fen;
    private String solution;
    private int difficulty;

    public ChessTask(int id, String fen, String solution, int difficulty) {
        this.id = id;
        this.fen = fen;
        this.solution = solution;
        this.difficulty = difficulty;

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

    public String getSolution() {
        return solution;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "ChessTask{" +
                "id=" + id +
                ", fen='" + fen + '\'' +
                ", solution='" + solution + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}

