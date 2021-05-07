package core;

import db.QuestionDao;
import db.ResultDao;
import db.UserDao;
import model.Question;
import model.Result;
import model.User;

public class DatabaseManager {
    private final UserDao userDao;
    private final QuestionDao questionDao;
    private final ResultDao resultDao;

    public DatabaseManager() {
        userDao = new UserDao();
        questionDao = new QuestionDao();
        resultDao = new ResultDao();
    }

    public void addUser(User newUser) throws RuntimeException {
        if (userDao.contains(newUser)) {
            throw new RuntimeException("Пользователь с таким именем уже существует!");
        }
        userDao.addUser(newUser);
    }

    public User findUser(String name) {
        return userDao.findUser(name);
    }

    public void updateUser(String name, User updatedUser) {
        userDao.updateUser(name, updatedUser);
    }

    public void deleteUser(String name) {
        userDao.deleteUser(name);
    }

    public long getUsersSize() {
        return userDao.getSize();
    }


    public void addQuestion(Question newQuestion) throws RuntimeException {
        if (questionDao.contains(newQuestion)) {
            throw new RuntimeException("Такой вопрос уже существует!");
        }
        questionDao.addQuestion(newQuestion);
    }

    public Question findQuestion(String question) {
        return questionDao.findQuestion(question);
    }

    public void updateQuestion(String question, Question updatedQuestion) {
        questionDao.updateQuestion(question, updatedQuestion);
    }

    public void deleteQuestion(String question) {
        questionDao.deleteQuestion(question);
    }

    public long getQuestionsSize() {
        return questionDao.getSize();
    }

    public Question[] getAllQuestions() {
        return questionDao.getAll();
    }


    public void addResult(Result newResult) {
        if (!resultDao.contains(newResult)) {
            resultDao.addResult(newResult);
        }
    }

    public Result findResult(String name) {
        return resultDao.findResult(name);
    }

    public void updateResult(String name, Result updatedResult) {
        resultDao.updateResult(name, updatedResult);
    }

    public void deleteResult(String name) {
        resultDao.deleteResult(name);
    }

    public long getResultsSize() {
        return resultDao.getSize();
    }

    public Result[] getAllResults() {
        return resultDao.getAll();
    }
}
