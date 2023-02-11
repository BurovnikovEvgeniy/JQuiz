package question;

import java.io.IOException;

import core.exceptions.EmptyPasswordException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;

public class TestAddExistQuestions {

    private static final String stringQuestion = RandomStringUtils.randomAlphabetic(10) + " ?";
    private static final String[] answers = new String[] {
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5)
    };
    private static final Question existInBaseQuestion = new Question(stringQuestion, answers, 2);
    private Question newExistQuestion;
    private DatabaseManager databaseManager;


    @Before
    public void setUp() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addQuestion(existInBaseQuestion);
        newExistQuestion = existInBaseQuestion;
    }

    @Test (expected = QuestionAlreadyExistsException.class)
    public void testAddExistQuestions() throws QuestionAlreadyExistsException {
        databaseManager.addQuestion(newExistQuestion);
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
