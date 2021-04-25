import db.QuestionSerializer;
import db.ResultsSerializer;
import db.UserSerializer;
import model.Question;
import model.Results;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.Date;
import java.util.List;


public class OldMapDatabaseIT {

    private DB usersDB;
    private DB questionsDB;
    private DB resultsDB;

    private List<User> users;
    private List<Question> questions;
    private List<Results> results;

    @Before
    public void before() {
        usersDB = DBMaker.fileDB(new File("src/main/resources/users.db")).make();
        users = usersDB.indexTreeList("usersList", new UserSerializer()).createOrOpen();

        questionsDB = DBMaker.fileDB(new File("src/main/resources/questions.db")).make();
        questions = usersDB.indexTreeList("questionsList", new QuestionSerializer()).createOrOpen();

        resultsDB = DBMaker.fileDB(new File("src/main/resources/results.db")).make();
        results = usersDB.indexTreeList("resultsList", new ResultsSerializer()).createOrOpen();
    }

    @Test
    public void addOneUser() {
        long size = users.size();
        User user = new User("vanya", "qwerty");
        users.add(user);
        Assert.assertEquals(size + 1, users.size());
    }

    @Test
    public void addManyUsers() {
        long size = users.size();

        users.add(new User("vanya", "qwerty"));
        users.add(new User("petya", "1234"));
        users.add(new User("masha", "hrGS9SbWze"));

        Assert.assertEquals(size + 3, users.size());
    }

    @Test
    public void addOneQuestion() {
        long size = questions.size();
        questions.add(new Question("How many?", new String[]{"one", "two", "three", "four"}, 2));
        Assert.assertEquals(size + 1, questions.size());
    }

    @Test
    public void addManyQuestion() {
        long size = questions.size();

        questions.add(new Question("Question1?", new String[]{"one", "two", "three", "four"}, 2));
        questions.add(new Question("Question2?", new String[]{"one", "two", "three", "four"}, 1));
        questions.add(new Question("Question3?", new String[]{"one", "two", "three", "four"}, 4));
        questions.add(new Question("Question4?", new String[]{"one", "two", "three", "four"}, 1));

        Assert.assertEquals(size + 4, questions.size());
    }

    @Test
    public void addOneResult() {
        long size = results.size();
        results.add(new Results("vanya", new Date(), 100));
        Assert.assertEquals(size + 1, results.size());
    }

    @Test
    public void addManyResults() {
        long size = results.size();

        results.add(new Results("vanya", new Date(), 80));
        results.add(new Results("petya", new Date(), 100));
        results.add(new Results("masha", new Date(), 120));

        Assert.assertEquals(size + 3, results.size());
    }

    @Test
    public void findExistingUser() {
        String requiredLogin = "vanya";
        User requiredUser = null;

        for (User user : users) {
            if (user.getLogin().equals(requiredLogin)) {
                requiredUser = user;
                break;
            }
        }

        Assert.assertNotNull(requiredUser);
    }

    @Test
    public void findNotExistingUser() {
        String requiredLogin = "kolya";
        User requiredUser = null;

        for (User user : users) {
            if (user.getLogin().equals(requiredLogin)) {
                requiredUser = user;
                break;
            }
        }

        Assert.assertNull(requiredUser);
    }

    @Test
    public void findExistingQuestion() {
        String requiredQuestionText = "How many?";
        Question requiredQuestion = null;

        for (Question question : questions) {
            if (question.getQuestion().equals(requiredQuestionText)) {
                requiredQuestion = question;
                break;
            }
        }

        Assert.assertNotNull(requiredQuestion);
    }

    @Test
    public void findNotExistingQuestion() {
        String requiredQuestionText = "QuestionDoesNotExist?";
        Question requiredQuestion = null;

        for (Question question : questions) {
            if (question.getQuestion().equals(requiredQuestionText)) {
                requiredQuestion = question;
                break;
            }
        }

        Assert.assertNull(requiredQuestion);
    }

    @Test
    public void findExistingResult() {
        String requiredLogin = "vanya";
        Results requiredResult = null;

        for (Results result : results) {
            if (result.getLogin().equals(requiredLogin)) {
                requiredResult = result;
                break;
            }
        }

        Assert.assertNotNull(requiredResult);
    }

    @Test
    public void findNotExistingResult() {
        String requiredLogin = "kolya";
        Results requiredResult = null;

        for (Results result : results) {
            if (result.getLogin().equals(requiredLogin)) {
                requiredResult = result;
                break;
            }
        }

        Assert.assertNull(requiredResult);
    }

    @Test
    public void updateUser() {
        String requiredLogin = "vanya";
        User newUser = new User("vanya", "1234");
        User requiredUser = null;

        for (User user : users) {
            if (user.getLogin().equals(requiredLogin)) {
                user = newUser;
                break;
            }
        }

        for (User user : users) {
            if (user.getLogin().equals(requiredLogin)) {
                Assert.assertEquals("1234", user.getPassword());
            }
        }

    }

    @Test
    public void updateQuestion() {

        String requiredQuestionText = "How many?";
        Question newQuestion = new Question("How many?", new String[]{"one", "two", "three", "four"}, 3);

        for (Question question : questions) {
            if (question.getQuestion().equals(requiredQuestionText)) {
                question = newQuestion;
                break;
            }
        }

        for (Question question : questions) {
            if (question.getQuestion().equals(requiredQuestionText)) {
                Assert.assertEquals(3, question.getCorrectAnswerIndex());
                break;
            }
        }
    }

    @Test
    public void updateResult() {

        String requiredLogin = "vanya";
        Results newResult = new Results("vanya", new Date(), 50);

        for (Results result : results) {
            if (result.getLogin().equals(requiredLogin)) {
                result = newResult;
                break;
            }
        }

        for (Results result : results) {
            if (result.getLogin().equals(requiredLogin)) {
                Assert.assertEquals(50, result.getScore());
                break;
            }
        }

    }


    @Test
    public void deleteUser() {
        long size = users.size();
        String requiredLogin = "vanya";

        for (User user : users) {
            if (user.getLogin().equals(requiredLogin)) {
                users.remove(user);
                break;
            }
        }

        Assert.assertEquals(size - 1, users.size());
    }

    @Test
    public void deleteQuestion() {
        long size = questions.size();
        String requiredQuestionText = "How many?";

        for (Question question : questions) {
            if (question.getQuestion().equals(requiredQuestionText)) {
                questions.remove(question);
                break;
            }
        }

        Assert.assertEquals(size - 1, users.size());
    }

    @Test
    public void deleteResult() {
        long size = results.size();
        String requiredLogin = "vanya";

        for (Results result : results) {
            if (result.getLogin().equals(requiredLogin)) {
                results.remove(result);
                break;
            }
        }

        Assert.assertEquals(size - 1, users.size());
    }

    @After
    public void after() {
        usersDB.close();
        questionsDB.close();
        resultsDB.close();
    }
}
