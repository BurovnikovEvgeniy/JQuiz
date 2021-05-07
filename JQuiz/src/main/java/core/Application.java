package core;

import model.Question;
import ui.MainFrame;

import java.io.IOException;

public class Application {

    public void start() {
        DatabaseManager databaseManager = new DatabaseManager();

        try {
            databaseManager.createDbDirectory();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        addQuestionsForDebugOnly(databaseManager);

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    private void addQuestionsForDebugOnly(DatabaseManager databaseManager) {
        if (databaseManager.getQuestionsSize() == 0) {
            String[] answers = new String[]{"1", "2", "3", "4"};
            try {
                databaseManager.addQuestion(new Question("1?", answers, 0));
                databaseManager.addQuestion(new Question("2?", answers, 0));
                databaseManager.addQuestion(new Question("3?", answers, 0));
                databaseManager.addQuestion(new Question("4?", answers, 0));
                databaseManager.addQuestion(new Question("5?", answers, 0));
                databaseManager.addQuestion(new Question("6?", answers, 0));
                databaseManager.addQuestion(new Question("7?", answers, 0));
                databaseManager.addQuestion(new Question("8?", answers, 0));
                databaseManager.addQuestion(new Question("9?", answers, 0));
                databaseManager.addQuestion(new Question("10?", answers, 0));
                databaseManager.addQuestion(new Question("11?", answers, 0));
                databaseManager.addQuestion(new Question("12?", answers, 0));
                databaseManager.addQuestion(new Question("13?", answers, 0));
                databaseManager.addQuestion(new Question("14?", answers, 0));
                databaseManager.addQuestion(new Question("15?", answers, 0));
                databaseManager.addQuestion(new Question("16?", answers,    0));
                databaseManager.addQuestion(new Question("17?", answers,    0));
                databaseManager.addQuestion(new Question("18?", answers,    0));
                databaseManager.addQuestion(new Question("19?", answers,    0));
                databaseManager.addQuestion(new Question("20?", answers,    0));

            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

}
