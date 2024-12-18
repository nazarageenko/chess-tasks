### Тест кейсы ver1.0


## Регистрация пользователя

**TC001: Проверка успешной регистрации нового пользователя с корректными данными**
- **Шаги:**
    1. Открыть приложение.
    2. Ввести уникальное имя пользователя.
    3. Ввести пароль.
    4. Нажать кнопку "Зарегистрироваться".
- **Ожидаемый результат:** Пользователь успешно зарегистрирован, отображается сообщение об успешной регистрации.

**TC002: Проверка сообщения об ошибке при попытке регистрации с уже существующим именем пользователя**
- **Шаги:**
    1. Открыть приложение.
    2. Ввести имя пользователя, которое уже существует в базе данных.
    3. Ввести пароль.
    4. Нажать кнопку "Зарегистрироваться".
- **Ожидаемый результат:** Отображается сообщение об ошибке о том, что имя пользователя уже существует.

**TC003: Проверка обработки ошибок при вводе некорректных данных**
- **Шаги:**
    1. Открыть приложение.
    2. Оставить поле имени пользователя пустым.
    3. Ввести пароль.
    4. Нажать кнопку "Зарегистрироваться".
- **Ожидаемый результат:** Отображается сообщение об ошибке о том, что имя пользователя не может быть пустым.

## Аутентификация пользователя

**TC004: Проверка успешного входа зарегистрированного пользователя**
- **Шаги:**
    1. Открыть приложение.
    2. Ввести существующее имя пользователя.
    3. Ввести правильный пароль.
    4. Нажать кнопку "Войти".
- **Ожидаемый результат:** Пользователь успешно вошел в систему, отображается сообщение о входе.

**TC005: Проверка сообщения об ошибке для неверных учетных данных**
- **Шаги:**
    1. Открыть приложение.
    2. Ввести существующее имя пользователя.
    3. Ввести неверный пароль.
    4. Нажать кнопку "Войти".
- **Ожидаемый результат:** Отображается сообщение об ошибке о неверных учетных данных.

**TC006: Проверка реакции системы при пустых полях имени пользователя или пароля**
- **Шаги:**
    1. Открыть приложение.
    2. Оставить поле имени пользователя или пароля пустым.
    3. Нажать кнопку "Войти".
- **Ожидаемый результат:** Отображается сообщение об ошибке о том, что поля не могут быть пустыми.

## Получение шахматных задач

**TC007: Проверка получения и отображения задачи для каждого уровня сложности**
- **Шаги:**
    1. Войти в приложение.
    2. Выбрать уровень сложности задачи.
    3. Нажать кнопку "Получить новую задачу".
- **Ожидаемый результат:** Отображается новая задача соответствующего уровня сложности.

**TC008: Проверка корректности отображаемой строки FEN**
- **Шаги:**
    1. Войти в приложение.
    2. Получить новую задачу.
- **Ожидаемый результат:** Строка FEN отображается корректно и соответствует текущей задаче.

**TC009: Проверка корректного отображения задачи на шахматной доске**
- **Шаги:**
    1. Войти в приложение.
    2. Получить новую задачу.
- **Ожидаемый результат:** Задача корректно отображается на шахматной доске.

## Проверка решений задач

**TC010: Проверка правильной проверки корректного решения**
- **Шаги:**
    1. Войти в приложение.
    2. Получить новую задачу.
    3. Ввести правильное решение.
    4. Нажать кнопку "Отправить ответ".
- **Ожидаемый результат:** Отображается сообщение о правильном решении, рейтинг пользователя увеличивается.

**TC011: Проверка правильной проверки некорректного решения**
- **Шаги:**
    1. Войти в приложение.
    2. Получить новую задачу.
    3. Ввести неправильное решение.
    4. Нажать кнопку "Отправить ответ".
- **Ожидаемый результат:** Отображается сообщение о неправильном решении, рейтинг пользователя уменьшается.

**TC012: Проверка обновления рейтинга пользователя после правильного решения задачи**
- **Шаги:**
    1. Войти в приложение.
    2. Получить новую задачу.
    3. Ввести правильное решение.
    4. Нажать кнопку "Отправить ответ".
- **Ожидаемый результат:** Рейтинг пользователя увеличивается на соответствующее количество баллов.

**TC013: Проверка обновления рейтинга пользователя после неправильного решения задачи**
- **Шаги:**
    1. Войти в приложение.
    2. Получить новую задачу.
    3. Ввести неправильное решение.
    4. Нажать кнопку "Отправить ответ".
- **Ожидаемый результат:** Рейтинг пользователя уменьшается на соответствующее количество баллов.

## Лидерборд

**TC014: Проверка отображения топ-10 пользователей по рейтингу**
- **Шаги:**
    1. Войти в приложение.
    2. Перейти к разделу лидерборда.
- **Ожидаемый результат:** Отображаются топ-10 пользователей по рейтингу.

**TC015: Проверка отображения текущего пользователя, если он не входит в топ-10**
- **Шаги:**
    1. Войти в приложение под учетной записью, не входящей в топ-10.
    2. Перейти к разделу лидерборда.
- **Ожидаемый результат:** Отображается текущий пользователь в 11-й строке с его позицией и рейтингом.

**TC016: Проверка корректности отображаемых данных**
- **Шаги:**
    1. Войти в приложение.
    2. Перейти к разделу лидерборда.
- **Ожидаемый результат:** Отображаются корректные данные (номер в рейтинге, имя пользователя, рейтинг) для всех пользователей.

## Пользовательский интерфейс

**TC017: Проверка корректного отображения всех элементов интерфейса**
- **Шаги:**
    1. Открыть приложение.
- **Ожидаемый результат:** Все элементы интерфейса корректно отображаются и расположены.

**TC018: Проверка функциональности всех кнопок и полей ввода**
- **Шаги:**
    1. Войти в приложение.
    2. Проверить функциональность всех кнопок и полей ввода.
- **Ожидаемый результат:** Все кнопки и поля ввода работают корректно.

**TC019: Проверка читаемости и удобства использования интерфейса**
- **Шаги:**
    1. Открыть приложение.
- **Ожидаемый результат:** Интерфейс читаем и удобен для пользователя, все элементы доступны и понятны.

## Нефункциональные тесты

**Производительность:**

**TC020: Проверка загрузки приложения в допустимые временные рамки**
- **Шаги:** Открыть приложение.
- **Ожидаемый результат:** Приложение загружается в течение допустимого времени (например, не более 5 секунд).

**TC021: Проверка времени отклика для ключевых операций**
- **Шаги:** Выполнить основные операции (регистрация, вход, получение задачи).
- **Ожидаемый результат:** Время отклика для каждой операции не превышает допустимые значения (например, 2 секунды).

**Совместимость:**

**TC022: Проверка работы приложения на различных операционных системах**
- **Шаги:** Запустить приложение на Windows, macOS и Linux.
- **Ожидаемый результат:** Приложение работает корректно на всех операционных системах.

**TC023: Проверка работы приложения на различных версиях Java**
- **Шаги:** Запустить приложение на Java 8 и Java 11.
- **Ожидаемый результат:** Приложение работает корректно на всех версиях Java.

**Безопасность:**

**TC024: Проверка защиты от SQL-инъекций в формах регистрации и аутентификации**
- **Шаги:** Попробовать выполнить SQL-инъекции в полях ввода формы регистрации и аутентификации.
- **Ожидаемый результат:** Система корректно обрабатывает ввод и предотвращает SQL-инъекции.

**TC025: Проверка хэширования и хранения паролей в базе данных**
- **Шаги:** Зарегистрировать нового пользователя и проверить хэширование пароля в базе данных.
- **Ожидаемый результат:** Пароли хранятся в хэшированном виде.

**TC026: Проверка защиты от несанкционированного доступа к данным**
- **Шаги:** Попробовать получить доступ к данным без авторизации.
- **Ожидаемый результат:** Система предотвращает доступ к данным без авторизации и отображает сообщение об ошибке или перенаправляет на страницу входа.

