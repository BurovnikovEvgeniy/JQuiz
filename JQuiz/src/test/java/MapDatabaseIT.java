import db.DatabaseManager;
import model.Question;
import model.Results;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;


public class MapDatabaseIT {

    private DatabaseManager databaseManager;

    @Before
    public void before() {
        databaseManager = new DatabaseManager();
    }

    @Test
    public void test() {
        addOneUser();
        addManyUsers();
        addOneQuestion();
        addManyQuestions();
        addOneResult();
        addManyResults();

        findExistingUser();
        findNotExistingUser();
        findExistingQuestion();
        findNotExistingQuestion();
        findExistingResult();
        findNotExistingResult();

        updateUser();
        updateQuestion();
        updateResult();

        deleteUser();
        deleteQuestion();
        deleteResult();
    }

    @Test
    public void addOneUser() {
        long size = databaseManager.getUsersSize();
        databaseManager.addUser(new User("vanya", "qwerty"));
        Assert.assertEquals(size + 1, databaseManager.getUsersSize());
    }

    @Test
    public void addManyUsers() {
        long size = databaseManager.getUsersSize();

        databaseManager.addUser(new User("petya", "1234"));
        databaseManager.addUser(new User("masha", "hrGS9SbWze"));

        Assert.assertEquals(size + 2, databaseManager.getUsersSize());
    }

    @Test
    public void addOneQuestion() {
        long size = databaseManager.getQuestionsSize();
        databaseManager.addQuestion(new Question("How many?", new String[]{"one", "two", "three", "four"}, 2));
        Assert.assertEquals(size + 1, databaseManager.getQuestionsSize());
    }

    @Test
    public void addManyQuestions() {
        long size = databaseManager.getQuestionsSize();

        databaseManager.addQuestion(new Question("Question1?", new String[]{"one", "two", "three", "four"}, 2));
        databaseManager.addQuestion(new Question("Question2?", new String[]{"one", "two", "three", "four"}, 1));
        databaseManager.addQuestion(new Question("Question3?", new String[]{"one", "two", "three", "four"}, 4));
        databaseManager.addQuestion(new Question("Question4?", new String[]{"one", "two", "three", "four"}, 1));

        Assert.assertEquals(size + 4, databaseManager.getQuestionsSize());
    }

    @Test
    public void addOneResult() {
        long size = databaseManager.getResultsSize();
        databaseManager.addResult(new Results("vanya", new Date(), 100));
        Assert.assertEquals(size + 1, databaseManager.getResultsSize());
    }

    @Test
    public void addManyResults() {
        long size = databaseManager.getResultsSize();

        databaseManager.addResult(new Results("vanya", new Date(), 80));
        databaseManager.addResult(new Results("petya", new Date(), 100));
        databaseManager.addResult(new Results("masha", new Date(), 120));

        Assert.assertEquals(size + 3, databaseManager.getResultsSize());
    }

    @Test
    public void findExistingUser() {
        String requiredLogin = "vanya";
        User requiredUser = databaseManager.findUser(requiredLogin);
        Assert.assertNotNull(requiredUser);
    }

    @Test
    public void findNotExistingUser() {
        String requiredLogin = "kolya";
        User requiredUser = databaseManager.findUser(requiredLogin);
        Assert.assertNull(requiredUser);
    }

    @Test
    public void findExistingQuestion() {
        String requiredQuestionText = "How many?";
        Question requiredQuestion = databaseManager.findQuestion(requiredQuestionText);
        Assert.assertNotNull(requiredQuestion);
    }

    @Test
    public void findNotExistingQuestion() {
        String requiredQuestionText = "QuestionDoesNotExist?";
        Question requiredQuestion = databaseManager.findQuestion(requiredQuestionText);
        Assert.assertNull(requiredQuestion);
    }

    @Test
    public void findExistingResult() {
        String requiredLogin = "vanya";
        Results requiredResult = databaseManager.findResult(requiredLogin);
        Assert.assertNotNull(requiredResult);
    }

    @Test
    public void findNotExistingResult() {
        String requiredLogin = "kolya";
        Results requiredResult = databaseManager.findResult(requiredLogin);
        Assert.assertNull(requiredResult);
    }

    @Test
    public void updateUser() {
        String requiredLogin = "vanya";
        User newUser = new User("vanya", "1234");
        databaseManager.updateUser(requiredLogin, newUser);
        Assert.assertEquals("1234", databaseManager.findUser("vanya").getPassword());
    }

    @Test
    public void updateQuestion() {
        String requiredQuestionText = "How many?";
        Question newQuestion = new Question("How many?", new String[]{"one", "two", "three", "four"}, 3);
        databaseManager.updateQuestion(requiredQuestionText, newQuestion);
        Assert.assertEquals(3, databaseManager.findQuestion(requiredQuestionText).getCorrectAnswerIndex());
    }

    @Test
    public void updateResult() {
        String requiredLogin = "vanya";
        Results newResult = new Results("vanya", new Date(), 50);
        databaseManager.updateResult(requiredLogin, newResult);
        Assert.assertEquals(50, databaseManager.findResult(requiredLogin).getScore());
    }


    @Test
    public void deleteUser() {
        long size = databaseManager.getUsersSize();
        String requiredLogin = "vanya";
        databaseManager.deleteUser(requiredLogin);
        Assert.assertEquals(size - 1, databaseManager.getUsersSize());
    }

    @Test
    public void deleteQuestion() {
        long size = databaseManager.getQuestionsSize();
        String requiredQuestionText = "How many?";
        databaseManager.deleteQuestion(requiredQuestionText);
        Assert.assertEquals(size - 1, databaseManager.getQuestionsSize());
    }

    @Test
    public void deleteResult() {
        long size = databaseManager.getResultsSize();
        String requiredLogin = "vanya";
        databaseManager.deleteResult(requiredLogin);
        Assert.assertEquals(size - 1, databaseManager.getResultsSize());
    }

    @After
    public void after() {
        databaseManager.close();
    }
}
