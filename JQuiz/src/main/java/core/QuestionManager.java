package core;

import core.exceptions.QuestionAlreadyExistsException;
import model.Question;

public class QuestionManager {

    private final DatabaseManager databaseManager;

    public QuestionManager() {
        this.databaseManager = new DatabaseManager();
    }

    public Question[] getAllQuestions() {
        return databaseManager.getAllQuestions();
    }

    public void deleteQuestion(Question question) {
        databaseManager.deleteQuestion(question.getQuestion());
    }

    public void addQuestion(Question question) throws QuestionAlreadyExistsException {
        databaseManager.addQuestion(question);
    }
}
