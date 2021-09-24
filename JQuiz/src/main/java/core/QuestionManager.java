package core;

import core.exceptions.QuestionAlreadyExistsException;
import model.Question;

public class QuestionManager {

    private final DatabaseManager databaseManager;

    public QuestionManager(String dbFolderName) {
        this.databaseManager = new DatabaseManager(dbFolderName);
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

    public void updateQuestion(Question oldQuestion, Question newQuestion) {
        databaseManager.updateQuestion(oldQuestion.getQuestion(), newQuestion);
    }
}
