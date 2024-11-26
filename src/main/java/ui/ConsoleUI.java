package ui;

import users.UserManager;
import database.TaskRepository;
import models.ChessTask;
import java.util.Scanner;
import logic.TaskGenerator;

public class ConsoleUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в шахматное приложение!");
        System.out.println("Чтобы продолжить, вам нужно войти в систему или зарегистрироваться.");
        System.out.println("Введите 'register' для регистрации или 'login' для входа:");

        boolean isAuthenticated = false;
        String username = null;

        // Аутентификация пользователя
        while (!isAuthenticated) {
            String action = scanner.nextLine().trim().toLowerCase();

            if (action.equals("register")) {
                // Регистрация пользователя
                System.out.println("Введите ваш логин:");
                String newUsername = scanner.nextLine().trim();

                System.out.println("Введите ваш пароль:");
                String newPassword = scanner.nextLine().trim();

                if (UserManager.registerUser(newUsername, newPassword)) {
                    System.out.println("Регистрация прошла успешно! Теперь вы можете войти в систему.");
                } else {
                    System.out.println("Ошибка при регистрации. Возможно, пользователь с таким логином уже существует.");
                }
            } else if (action.equals("login")) {
                // Вход пользователя
                System.out.println("Введите ваш логин:");
                String loginUsername = scanner.nextLine().trim();

                System.out.println("Введите ваш пароль:");
                String loginPassword = scanner.nextLine().trim();

                if (UserManager.authenticateUser(loginUsername, loginPassword)) {
                    System.out.println("Вход выполнен успешно! Добро пожаловать, " + loginUsername + "!");
                    isAuthenticated = true;
                    username = loginUsername;
                } else {
                    System.out.println("Неверный логин или пароль. Попробуйте ещё раз.");
                }
            } else {
                System.out.println("Неизвестная команда. Введите 'register' или 'login'.");
            }
        }

        // Получение рейтинга пользователя
        int userRating = UserManager.getUserRating(username);
        System.out.println("Ваш текущий рейтинг: " + userRating);

        System.out.println("Задача: решить шахматную задачу. Попробуйте угадать правильный ход.");
        System.out.println("Введите 'exit', чтобы выйти.");

        while (true) {
            // Получаем случайную задачу с помощью TaskGenerator
            ChessTask task = TaskGenerator.getRandomTask();

            if (task == null) {
                System.out.println("В базе данных нет доступных задач.");
                break;
            }

            // Отображаем задачу
            System.out.println("\nFEN: " + task.getFen());
            System.out.println("Введите ваш ход (например, Qb3, e5, и т.д.):");

            String userInput = scanner.nextLine();

            // Выход из программы
            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("Выход из приложения. До свидания!");
                break;
            }

            // Проверяем решение
            if (userInput.equalsIgnoreCase(task.getSolution())) {
                System.out.println("Поздравляем! Вы решили задачу правильно.");
                userRating += 10; // Увеличиваем рейтинг за правильный ответ
                UserManager.updateUserRating(username, userRating);
                System.out.println("Ваш новый рейтинг: " + userRating);
            } else {
                System.out.println("Неверный ход. Правильный ответ: " + task.getSolution());
                userRating -= 10; // Уменьшаем рейтинг за неправильный ответ
                UserManager.updateUserRating(username, userRating);
                System.out.println("Ваш новый рейтинг: " + userRating);
            }

            System.out.println("\nХотите попробовать ещё раз? (да/нет)");
            String retry = scanner.nextLine();
            if (!"да".equalsIgnoreCase(retry)) {
                System.out.println("Спасибо за игру. До свидания!");
                break;
            }
        }

        scanner.close();
    }
}
