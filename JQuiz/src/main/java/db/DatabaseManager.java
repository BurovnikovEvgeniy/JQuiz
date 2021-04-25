package db;

import model.Question;
import model.Results;
import model.User;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DatabaseManager {

    private final DB usersDB;
    private final DB questionsDB;
    private final DB resultsDB;

    private List<User> users;
    private List<Question> questions;
    private List<Results> results;

    public DatabaseManager() {
        usersDB = DBMaker.fileDB(new File("src/main/resources/users.db")).make();
        users = usersDB.indexTreeList("usersList", new UserSerializer()).createOrOpen();

        questionsDB = DBMaker.fileDB(new File("src/main/resources/questions.db")).make();
        questions = usersDB.indexTreeList("questionsList", new QuestionSerializer()).createOrOpen();

        resultsDB = DBMaker.fileDB(new File("src/main/resources/results.db")).make();
        results = usersDB.indexTreeList("resultsList", new ResultsSerializer()).createOrOpen();
    }

    public void open() {
        users = usersDB.indexTreeList("usersList", new UserSerializer()).open();
        questions = usersDB.indexTreeList("questionsList", new QuestionSerializer()).open();
        results = usersDB.indexTreeList("resultsList", new ResultsSerializer()).open();
    }

    public void close() {
        usersDB.close();
        questionsDB.close();
        resultsDB.close();
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }

    public User findUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(String login, User updatedUser) {
        int i = findUserIndex(findUser(login));
        users.remove(i);
        users.add(i, updatedUser);
    }

    public void deleteUser(String login) {
        users.remove(findUserIndex(findUser(login)));
    }

    public void addQuestion(Question newQuestion) {
        questions.add(newQuestion);
    }

    public Question findQuestion(String question) {
        for (Question question1 : questions) {
            if (question1.getQuestion().equals(question)) {
                return question1;
            }
        }
        return null;
    }

    public void updateQuestion(String question, Question updatedQuestion) {
        int i = findQuestionIndex(findQuestion(question));
        questions.remove(i);
        questions.add(i, updatedQuestion);
    }

    public void deleteQuestion(String question) {
        questions.remove(findQuestionIndex(findQuestion(question)));
    }

    public void addResult(Results newResult) {
        results.add(newResult);
    }

    public Results findResult(String login) {
        for (Results result : results) {
            if (result.getLogin().equals(login)) {
                return result;
            }
        }
        return null;
    }

    public void updateResult(String login, Results updatedResult) {
        int i = findResultIndex(findResult(login));
        results.remove(i);
        results.add(i, updatedResult);
    }

    public void deleteResult(String login) {
        results.remove(findResultIndex(findResult(login)));
    }

    public long getUsersSize() {
        return users.size();
    }

    public long getQuestionsSize() {
        return questions.size();
    }

    public long getResultsSize() {
        return results.size();
    }

    private int findUserIndex(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(user.getLogin())
                    && users.get(i).getPassword().equals(user.getPassword())) {
                return i;
            }
        }
        return -1;
    }

    private int findQuestionIndex(Question question) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestion().equals(question.getQuestion())
                    && Arrays.equals(questions.get(i).getVariants(), question.getVariants())
                    && questions.get(i).getCorrectAnswerIndex() == question.getCorrectAnswerIndex()) {
                return i;
            }
        }
        return -1;
    }

    private int findResultIndex(Results result) {
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getLogin().equals(result.getLogin())
                    && results.get(i).getDate().equals(result.getDate())
                    && results.get(i).getScore() == result.getScore()) {
                return i;
            }
        }
        return -1;
    }

}
