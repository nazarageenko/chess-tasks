package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import users.UserManager;
import models.ChessTask;
import logic.TaskGenerator;

public class ConsoleUI {

    private String username = null;
    private int userRating = 1200; // Начальный рейтинг
    private ChessTask currentTask; // Текущая задача

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsoleUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        // Создание фрейма
        JFrame frame = new JFrame("chess-tasks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900); // Увеличенный размер фрейма

        // Основная панель с BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Панель для верхних элементов интерфейса
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Заголовок
        JLabel title = new JLabel("Добро пожаловать в шахматное приложение!");
        topPanel.add(title);

        // Для отображения задач и рейтинга
        JLabel fenLabel = new JLabel("FEN: Ожидайте задачи...");
        JLabel solutionLabel = new JLabel("Решение:...");
        JLabel ratingLabel = new JLabel("Ваш рейтинг: " + userRating);
        topPanel.add(fenLabel);
        topPanel.add(solutionLabel);
        topPanel.add(ratingLabel);

        // Поля для ввода логина и пароля
        JTextField loginField = new JTextField(15);
        loginField.setToolTipText("Введите ваш логин");
        topPanel.add(loginField);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setToolTipText("Введите ваш пароль");
        topPanel.add(passwordField);

        // Кнопки для входа и регистрации
        JButton registerButton = new JButton("Зарегистрироваться");
        JButton loginButton = new JButton("Войти");
        topPanel.add(registerButton);
        topPanel.add(loginButton);

        // Метки для уведомлений
        JLabel statusLabel = new JLabel("");
        topPanel.add(statusLabel);

        // Выбор сложности задачи
        JLabel difficultyLabel = new JLabel("Выберите сложность задачи:");
        String[] difficulties = {"1", "2", "3"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficulties);
        topPanel.add(difficultyLabel);
        topPanel.add(difficultyComboBox);

        // Кнопка для получения новой задачи
        JButton getNewTaskButton = new JButton("Получить новую задачу");
        getNewTaskButton.setEnabled(false);
        topPanel.add(getNewTaskButton);

        // Панель для шахматной доски
        String initialFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        ChessBoard chessBoard = new ChessBoard(initialFen);
        chessBoard.setVisible(true);

        // Панель для ввода ответа
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JTextField answerField = new JTextField(20);
        answerField.setToolTipText("Введите ваш ход");
        bottomPanel.add(answerField, BorderLayout.CENTER);

        JButton submitAnswerButton = new JButton("Отправить ответ");
        submitAnswerButton.setEnabled(false);
        bottomPanel.add(submitAnswerButton, BorderLayout.EAST);

        // Добавляем панели в основную панель
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(chessBoard), BorderLayout.CENTER); // Оборачиваем шахматную доску в JScrollPane
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Добавляем основную панель в фрейм
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);

        // Обработчик для кнопки регистрации
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                if (UserManager.registerUser(login, password)) {
                    statusLabel.setText("Регистрация прошла успешно! Теперь вы можете войти.");
                    registerButton.setVisible(false);
                } else {
                    statusLabel.setText("Ошибка при регистрации. Логин уже существует.");
                }
            }
        });

        // Обработчик для кнопки входа
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                if (UserManager.authenticateUser(login, password)) {
                    username = login;
                    userRating = UserManager.getUserRating(username);
                    statusLabel.setText("Вход выполнен успешно! Добро пожаловать, " + username);
                    ratingLabel.setText("Ваш рейтинг: " + userRating);
                    getNewTaskButton.setEnabled(true);
                    loginButton.setEnabled(false);
                    loginField.setVisible(false);
                    passwordField.setVisible(false);
                    registerButton.setVisible(false);
                } else {
                    statusLabel.setText("Неверный логин или пароль. Попробуйте ещё раз.");
                }
            }
        });

        // Обработчик для кнопки получения новой задачи
        getNewTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedDifficulty = Integer.parseInt((String) difficultyComboBox.getSelectedItem());
                ChessTask task = TaskGenerator.getRandomTaskByDifficulty(selectedDifficulty);
                if (task != null) {
                    currentTask = task; // Сохраняем текущую задачу
                    String fen = task.getFen();
                    System.out.println("FEN строка в ConsoleUI до переустановки: " + fen);

                    // Проверим, корректна ли FEN строка
                    if (fen == null || fen.trim().isEmpty()) {
                        System.out.println("Получена пустая или некорректная FEN строка, используем стандартную.");
                        fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"; // Стандартная FEN строка
                    }

                    fenLabel.setText("FEN: " + fen);
                    solutionLabel.setText("Решение: " + task.getSolution());
                    submitAnswerButton.setEnabled(true);

                    System.out.println("Перед вызовом setFEN, FEN строка: " + fen);
                    chessBoard.setFEN(fen);
                    chessBoard.setVisible(true);
                } else {
                    fenLabel.setText("Задачи не найдены.");
                }
            }
        });

        // Обработчик для кнопки отправки ответа
        submitAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = answerField.getText().trim();
                // Используем сохраненное решение текущей задачи
                String correctSolution = currentTask != null ? currentTask.getSolution() : "";
                if (userInput.equalsIgnoreCase(correctSolution)) {
                    userRating += 10;
                    statusLabel.setText("Правильный ответ! Ваш новый рейтинг: " + userRating);
                } else {
                    userRating -= 10;
                    statusLabel.setText("Неверный ответ. Правильный ответ: " + correctSolution + ". Ваш рейтинг: " + userRating);
                }
                ratingLabel.setText("Ваш рейтинг: " + userRating);
                UserManager.updateUserRating(username, userRating);
                answerField.setText("");
                submitAnswerButton.setEnabled(false);
            }
        });
    }
}
