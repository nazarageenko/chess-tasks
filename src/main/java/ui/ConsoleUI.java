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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsoleUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        // Создание фрейма
        JFrame frame = new JFrame("Шахматное приложение");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);

        // Панель для элементов интерфейса
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Заголовок
        JLabel title = new JLabel("Добро пожаловать в шахматное приложение!");
        panel.add(title);

        // Для отображения задач, решения и рейтинга
        JLabel fenLabel = new JLabel("FEN: Ожидайте задачи...");
        JLabel solutionLabel = new JLabel("Решение:...");
        JLabel ratingLabel = new JLabel("Ваш рейтинг: " + userRating);
        panel.add(fenLabel);
        panel.add(solutionLabel);
        panel.add(ratingLabel);

        // Поля для ввода логина и пароля с уменьшенным размером
        JTextField loginField = new JTextField(15);  // Уменьшаем поле для логина до 15 символов
        loginField.setToolTipText("Введите ваш логин");
        panel.add(loginField);

        JPasswordField passwordField = new JPasswordField(15);  // Уменьшаем поле для пароля до 15 символов
        passwordField.setToolTipText("Введите ваш пароль");
        panel.add(passwordField);

        // Кнопки для входа и регистрации
        JButton registerButton = new JButton("Зарегистрироваться");
        JButton loginButton = new JButton("Войти");
        panel.add(registerButton);
        panel.add(loginButton);

        // Метки для уведомлений
        JLabel statusLabel = new JLabel("");
        panel.add(statusLabel);

        // Кнопка для получения новой задачи
        JButton getNewTaskButton = new JButton("Получить новую задачу");
        getNewTaskButton.setEnabled(false); // Ожидаем, пока пользователь не войдёт
        panel.add(getNewTaskButton);

        // Поле для ввода ответа
        JTextField answerField = new JTextField(20);
        answerField.setToolTipText("Введите ваш ход");
        panel.add(answerField);

        JButton submitAnswerButton = new JButton("Отправить ответ");
        submitAnswerButton.setEnabled(false); // Ожидаем, пока задача не будет загружена
        panel.add(submitAnswerButton);

        // Обработчик для кнопки регистрации
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                if (UserManager.registerUser(login, password)) {
                    statusLabel.setText("Регистрация прошла успешно! Теперь вы можете войти.");
                    // Скрываем только кнопку регистрации, оставляем кнопку входа
                    registerButton.setVisible(false); // Скрываем кнопку регистрации
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
                    userRating = UserManager.getUserRating(username); // Получаем рейтинг из базы данных
                    statusLabel.setText("Вход выполнен успешно! Добро пожаловать, " + username);
                    ratingLabel.setText("Ваш рейтинг: " + userRating); // Отображаем рейтинг
                    getNewTaskButton.setEnabled(true); // Включаем кнопку для получения задач
                    loginButton.setEnabled(false); // Выключаем кнопку входа
                    // Скрываем поля для логина и пароля после успешного входа
                    loginField.setVisible(false);
                    passwordField.setVisible(false);
                    registerButton.setVisible(false); // Скрываем кнопку регистрации
                } else {
                    statusLabel.setText("Неверный логин или пароль. Попробуйте ещё раз.");
                }
            }
        });

        // Обработчик для кнопки получения новой задачи
        getNewTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessTask task = TaskGenerator.getRandomTask();
                if (task != null) {
                    fenLabel.setText("FEN: " + task.getFen());
                    solutionLabel.setText("Решение: " + task.getSolution());
                    submitAnswerButton.setEnabled(true); // Включаем кнопку отправки ответа
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
                String correctSolution = solutionLabel.getText().replace("Решение: ", "").trim();
                if (userInput.equalsIgnoreCase(correctSolution)) {
                    userRating += 10;
                    statusLabel.setText("Правильный ответ! Ваш новый рейтинг: " + userRating);
                } else {
                    userRating -= 10;
                    statusLabel.setText("Неверный ответ. Правильный ответ: " + correctSolution + ". Ваш рейтинг: " + userRating);
                }
                ratingLabel.setText("Ваш рейтинг: " + userRating);
                UserManager.updateUserRating(username, userRating); // Обновляем рейтинг в базе
                answerField.setText(""); // Очищаем поле для ответа
                submitAnswerButton.setEnabled(false); // Отключаем кнопку
            }
        });

        // Добавляем панель на фрейм
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
