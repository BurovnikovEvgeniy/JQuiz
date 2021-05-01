package core;

import model.Question;

public class QuestionManager {
    public Question[] getQuestions(int count) {
        Question[] question = new Question[count];
        String[] v = new String[]{"1", "2", "3", "4"};
        question[0] = new Question("What?", v, 2);
        question[1] = new Question("Why?", v, 0);
        return question;
    }

    public Question[] getAllQuestions() {
        Question[] question = new Question[2];
        String[] v = new String[]{"1", "2", "3", "4"};
        question[0] = new Question("What?", v, 2);
        question[1] = new Question("Why?", v, 0);
        return question;
    }

    public void deleteQuestion(Question question) {

    }

    public void addQuestion(Question question) {

    }
}
