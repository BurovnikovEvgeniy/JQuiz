package core;

import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import ui.MainFrame;

import java.io.IOException;

public class Application {

    public void start() {
        DatabaseManager databaseManager = new DatabaseManager("db");
        try {
            databaseManager.createDbDirectory();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        addQuestions(databaseManager);

        MainFrame frame = new MainFrame("db");
        frame.setVisible(true);
    }

    private void addQuestions(DatabaseManager databaseManager) {
        try {
            addQuestion(databaseManager,
                    "Что вернет выражение 1.0/0.0?",
                    "ArithmeticException",
                    "Double.INFINITY",
                    "NaN",
                    "-0.0",
                    1);
            addQuestion(databaseManager,
                    "Как избежать выполнения блока finally?",
                    "break в try/catch",
                    "Никак",
                    "return в try/catch",
                    "System.exit() в try/catch",
                    3);
            addQuestion(databaseManager,
                    "Для чего используется оператор NEW?",
                    "Для создания новой переменной",
                    "Для объявления нового класса",
                    "Для создания экземпляра класса",
                    "Это антагонист оператора OLD",
                    2);
            addQuestion(databaseManager,
                    "Как вызвать static-метод внутри обычного?",
                    "Никак",
                    "Перегрузить обычный метод",
                    "Переопределить обычный метод",
                    "Просто вызвать",
                    3);
            addQuestion(databaseManager,
                    "Как вызвать обычный метод класса внутри static-метода?",
                    "Никак",
                    "Перегрузить обычный метод",
                    "Переопределить обычный метод",
                    "Просто вызвать",
                    0);
            addQuestion(databaseManager,
                    "Что будет в результате выполнения операции (2 + 2 == 5 && 12 / 4 == 3 || 2 == 5 % 3)?",
                    "true",
                    "false",
                    "null",
                    "0",
                    0);
            addQuestion(databaseManager,
                    "Какой результат работы данного кода?\n" +
                            "public static void main(String[] args) { byte x = 127; x += 2; System.out.println(x); }",
                    "129",
                    "compile error",
                    "-127",
                    "runtime error",
                    2);
            addQuestion(databaseManager,
                    "Какой результат работы данного кода?\n" +
                            "public static void main(String[] args) { double a = 5; System.out.println(a / 2); }",
                    "runtime error",
                    "2.5",
                    "2",
                    "5 / 2",
                    1);
            addQuestion(databaseManager,
                    "Какой результат работы данного кода?\n" +
                            "public static void main(String[] args) { String s = \"Hello\"; System.out.println(s + (5 + 4)); }",
                    "Hello 5 4",
                    "Hello 54",
                    "Hello9",
                    "Hello54",
                    2);
            addQuestion(databaseManager,
                    "Какой результат работы данного кода?\n" +
                            "public static void main(String[] args) { String test = new String(\"Hello\"); String test2 = new String(\"Hello\"); " +
                            "System.out.println(test==test2); }",
                    "true",
                    "false",
                    "Hello",
                    "null",
                    1);
        } catch (QuestionAlreadyExistsException e) {
            System.err.println(e.getMessage());
        }
    }

    private void addQuestion(DatabaseManager dm, String question, String answer1, String answer2, String answer3, String answer4,
                             int correctIndex) throws QuestionAlreadyExistsException {
        if (!dm.isExistQuestion(question)) {
            String[] answers = new String[]{answer1, answer2, answer3, answer4};
            dm.addQuestion(new Question(question, answers, correctIndex));
        }
    }
}
