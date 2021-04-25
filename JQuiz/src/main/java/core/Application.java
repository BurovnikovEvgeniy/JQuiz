package core;

import model.Question;
import model.Results;
import ui.Frame;

public class Application {
    void start() {
        Frame frame = new Frame();
        frame.setVisible(true);
    }

    public static boolean isValidEnteredData(String login, String password, String message) {
        return (password.length() != 0 && login.length() != 0);
    }

    public static boolean register(String login, String password, String message) {
        return (password.length() != 0 && login.length() != 0);
    }

    public static Question[] getQuestions(int count) {
        Question[] question = new Question[count];
        String[] v = new String[]{"1", "2", "3", "4"};
        question[0] = new Question("What?", v, 2);
        question[1] = new Question("Why?", v, 0);
        return question;
    }

    public static void saveResults(Results results) {

    }
}
