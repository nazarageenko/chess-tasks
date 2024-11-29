package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard extends JPanel {
    private static final int SIZE = 8;
    private static final int TILE_SIZE = 80; // Размер клеток 80x80

    private String[][] board;
    private Map<String, ImageIcon> pieces;

    public ChessBoard(String fen) {
        board = new String[SIZE][SIZE];
        initBoardFromFEN(fen);
        pieces = new HashMap<>();
        loadPieceImages();
        setPreferredSize(new Dimension((SIZE + 2) * TILE_SIZE, (SIZE + 2) * TILE_SIZE)); // Устанавливаем размер панели с учетом координат
    }

    private void loadPieceImages() {
        pieces.put("P", new ImageIcon(getClass().getResource("/images/wP.png")));
        pieces.put("p", new ImageIcon(getClass().getResource("/images/bP.png")));
        pieces.put("K", new ImageIcon(getClass().getResource("/images/wK.png")));
        pieces.put("k", new ImageIcon(getClass().getResource("/images/bK.png")));
        pieces.put("R", new ImageIcon(getClass().getResource("/images/wR.png")));
        pieces.put("r", new ImageIcon(getClass().getResource("/images/bR.png")));
        pieces.put("N", new ImageIcon(getClass().getResource("/images/wN.png")));
        pieces.put("n", new ImageIcon(getClass().getResource("/images/bN.png")));
        pieces.put("B", new ImageIcon(getClass().getResource("/images/wB.png")));
        pieces.put("b", new ImageIcon(getClass().getResource("/images/bB.png")));
        pieces.put("Q", new ImageIcon(getClass().getResource("/images/wQ.png")));
        pieces.put("q", new ImageIcon(getClass().getResource("/images/bQ.png")));
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

        // Рисуем клетки шахматной доски
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect((j + 1) * TILE_SIZE, (i + 1) * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                String piece = board[i][j];
                if (piece != null && !piece.isEmpty()) {
                    ImageIcon icon = pieces.get(piece);
                    if (icon != null) {
                        icon.paintIcon(this, g, (j + 1) * TILE_SIZE, (i + 1) * TILE_SIZE);
                    }
                }
            }
        }

        // Рисуем буквы снизу и сверху
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        for (int j = 0; j < SIZE; j++) {
            String letter = String.valueOf((char) ('a' + j));
            g.drawString(letter, (j + 1) * TILE_SIZE + TILE_SIZE / 2 - 5, TILE_SIZE / 2 + 5); // Верх
            g.drawString(letter, (j + 1) * TILE_SIZE + TILE_SIZE / 2 - 5, (SIZE + 1) * TILE_SIZE + TILE_SIZE / 2 + 5); // Низ
        }

        // Рисуем цифры слева и справа
        for (int i = 0; i < SIZE; i++) {
            String number = String.valueOf(SIZE - i);
            g.drawString(number, TILE_SIZE / 2 - 5, (i + 1) * TILE_SIZE + TILE_SIZE / 2 + 5); // Лево
            g.drawString(number, (SIZE + 1) * TILE_SIZE + TILE_SIZE / 2 - 5, (i + 1) * TILE_SIZE + TILE_SIZE / 2 + 5); // Право
        }
    }

    public void setFEN(String fen) {
        System.out.println("Устанавливаем новую FEN строку: " + fen);
        initBoardFromFEN(fen);
        repaint();
    }

    public String getFEN() {
        // Генерация FEN строки на основе текущего состояния доски
        StringBuilder fen = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            int emptyCount = 0;
            for (int j = 0; j < SIZE; j++) {
                String piece = board[i][j];
                if (piece == null || piece.isEmpty()) {
                    emptyCount++;
                } else {
                    if (emptyCount > 0) {
                        fen.append(emptyCount);
                        emptyCount = 0;
                    }
                    fen.append(piece);
                }
            }
            if (emptyCount > 0) {
                fen.append(emptyCount);
            }
            if (i < SIZE - 1) {
                fen.append('/');
            }
        }
        return fen.toString();
    }
}
