package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard extends JPanel {
    private static final int SIZE = 8;
    private static final int TILE_SIZE = 60;

    private String[][] board;
    private Map<String, ImageIcon> pieces;

    public ChessBoard(String fen) {
        board = new String[SIZE][SIZE];
        initBoardFromFEN(fen);
        pieces = new HashMap<>();
        loadPieceImages();
    }

    private void loadPieceImages() {
        pieces.put("wP", new ImageIcon(getClass().getResource("/images/wP.png")));
        pieces.put("bP", new ImageIcon(getClass().getResource("/images/bP.png")));
        pieces.put("wK", new ImageIcon(getClass().getResource("/images/wK.png")));
        pieces.put("bK", new ImageIcon(getClass().getResource("/images/bK.png")));
        pieces.put("wR", new ImageIcon(getClass().getResource("/images/wR.png")));
        pieces.put("bR", new ImageIcon(getClass().getResource("/images/bR.png")));
        pieces.put("wN", new ImageIcon(getClass().getResource("/images/wN.png")));
        pieces.put("bN", new ImageIcon(getClass().getResource("/images/bN.png")));
        pieces.put("wB", new ImageIcon(getClass().getResource("/images/wB.png")));
        pieces.put("bB", new ImageIcon(getClass().getResource("/images/bB.png")));
        pieces.put("wQ", new ImageIcon(getClass().getResource("/images/wQ.png")));
        pieces.put("bQ", new ImageIcon(getClass().getResource("/images/bQ.png")));
    }

    private void initBoardFromFEN(String fen) {
        System.out.println("Получена FEN-строка в initBoardFromFEN: " + fen);

        if (fen == null || fen.trim().isEmpty()) {
            throw new IllegalArgumentException("FEN строка пустая или некорректная");
        }

        String[] parts = fen.split(" ");
        if (parts.length == 0) {
            throw new IllegalArgumentException("Некорректная FEN строка: " + fen);
        }

        String boardFen = parts[0];
        String[] rows = boardFen.split("/");

        System.out.println("Получено " + rows.length + " рядов в FEN строке.");

        if (rows.length != SIZE) {
            throw new IllegalArgumentException("Неверное количество рядов в FEN-строке. Ожидается 8 рядов, получено: " + rows.length);
        }

        for (int i = 0; i < SIZE; i++) {
            String row = rows[i];
            int colIndex = 0;

            for (int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);

                if (Character.isDigit(c)) {
                    int emptySpaces = Character.getNumericValue(c);
                    for (int k = 0; k < emptySpaces; k++) {
                        board[i][colIndex++] = "";  // Пустая клетка
                    }
                } else {
                    board[i][colIndex++] = String.valueOf(c);
                }
            }

            if (colIndex != SIZE) {
                throw new IllegalArgumentException("Неверное количество клеток в ряду: " + colIndex);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                String piece = board[i][j];
                if (piece != null && !piece.isEmpty()) {
                    ImageIcon icon = pieces.get(piece);
                    if (icon != null) {
                        icon.paintIcon(this, g, i * TILE_SIZE, j * TILE_SIZE);
                    }
                }
            }
        }
    }

    public void setFEN(String fen) {
        System.out.println("Устанавливаем новую FEN строку: " + fen);
        initBoardFromFEN(fen);
        repaint();
    }
}
