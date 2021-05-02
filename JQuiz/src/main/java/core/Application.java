package core;

import model.Question;
import ui.MainFrame;

public class Application {
    public void start() {
        DatabaseManager databaseManager = new DatabaseManager();

        if (databaseManager.getQuestionsSize() == 0) {
            String[] answers = new String[]{"1", "2", "3", "4"};
            try {
                databaseManager.addQuestion(new Question("What?", answers, 0));
                databaseManager.addQuestion(new Question("Wht?", answers, 2));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

}
