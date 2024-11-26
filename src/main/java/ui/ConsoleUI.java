package ui;

import users.UserManager;

import java.sql.Connection;
import java.util.Scanner;
import database.DatabaseConnection;
import database.TaskRepository;
import logic.TaskGenerator;
import models.ChessTask;


public class ConsoleUI {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Запрос действия: вход или регистрация
            System.out.println("Введите 'login' для входа или 'register' для регистрации:");
            String action = scanner.nextLine();

            if (action.equals("register")) {
                // Регистрация нового пользователя
                System.out.println("Введите логин:");
                String username = scanner.nextLine();

                System.out.println("Введите пароль:");
                String password = scanner.nextLine();

                if (UserManager.registerUser(username, password)) {
                    System.out.println("Регистрация прошла успешно!");
                } else {
                    System.out.println("Ошибка при регистрации. Возможно, этот логин уже занят.");
                }
            } else if (action.equals("login")) {
                // Вход
                System.out.println("Введите логин:");
                String username = scanner.nextLine();

                System.out.println("Введите пароль:");
                String password = scanner.nextLine();

                if (UserManager.authenticateUser(username, password)) {
                    int rating = UserManager.getUserRating(username);
                    System.out.println("Добро пожаловать, " + username + "!");
                    System.out.println("Ваш рейтинг: " + rating);
                    Connection conn = DatabaseConnection.connect();
                    ChessTask task = TaskGenerator.generateTask();
                    TaskRepository.saveTask(conn, task);

                    System.out.println("Сгенерирована задача: " + task.getFen());
                    System.out.println("Решение: " + task.getSolution());
                } else {
                    System.out.println("Неверный логин или пароль.");
                }
            } else {
                System.out.println("Неизвестная команда.");
            }
        }
}
        /*Connection conn = DatabaseConnection.connect();
        ChessTask task = TaskGenerator.generateTask();
        TaskRepository.saveTask(conn, task);

        System.out.println("Сгенерирована задача: " + task.getFen());
        System.out.println("Решение: " + task.getSolution());
        */
