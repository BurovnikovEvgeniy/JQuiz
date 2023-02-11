package question;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;

import static org.junit.Assert.assertEquals;

public class TestAddOneQuestion {

    private static final String stringQuestion = RandomStringUtils.randomAlphabetic(10) + " ?";
    private static final String[] answers = new String[] {
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5)
    };
    private static final Question question = new Question(stringQuestion, answers, 2);
    private DatabaseManager databaseManager;
    private QuestionManager questionManager;

    @Before
    public void setUp() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        questionManager = new QuestionManager("db_test");
    }

    @Test
    public void testAddExistQuestions() throws QuestionAlreadyExistsException {
        long size = databaseManager.getQuestionsSize();
        databaseManager.addQuestion(question);
        assertEquals(size + 1, databaseManager.getQuestionsSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
