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

import static org.junit.Assert.assertThrows;

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
    private QuestionManager questionManager;


    @Before
    public void setUp() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        questionManager = new QuestionManager("db_test");
        databaseManager.addQuestion(existInBaseQuestion);
        newExistQuestion = existInBaseQuestion;
    }

    @Test
    public void testAddExistQuestions() {
        assertThrows(QuestionAlreadyExistsException.class, () -> databaseManager.addQuestion(newExistQuestion));
    }

    @After
    public void after() throws IOException {
        databaseManager.deleteDbDirectory();
    }
}
