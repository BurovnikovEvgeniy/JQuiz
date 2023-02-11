package question;

import core.DatabaseManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestAddOneQuestion {
    private DatabaseManager databaseManager;
    private final Question preAddedQuestion = new Question("Which one?", new String[]{"one", "two", "three", "four"}, 2);

    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addQuestion(preAddedQuestion);
    }

    @Test
    public void testAddOneQuestion() throws QuestionAlreadyExistsException {
        long size = databaseManager.getQuestionsSize();
        databaseManager.addQuestion(new Question("How many?", new String[]{"one", "two", "three", "four"}, 2));
        assertEquals(size + 1, databaseManager.getQuestionsSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
