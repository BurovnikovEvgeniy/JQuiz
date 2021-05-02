package core;

import model.Question;

public class QuestionManager {

    DatabaseManager databaseManager;

    public QuestionManager() {
        this.databaseManager = new DatabaseManager();
    }

    public Question[] getAllQuestions() {
        return databaseManager.getAllQuestions();
    }

    public void deleteQuestion(Question question) {
        databaseManager.deleteQuestion(question.getQuestion());
    }

    public void addQuestion(Question question) {
        databaseManager.addQuestion(question);
    }
}
