package question;

import core.Application;
import core.DatabaseManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestCheckPreAddedQuestionsAmount {
    private DatabaseManager databaseManager;
    private final Application application = new Application();

    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
    }

    @Test
    public void testAddOneQuestion() {
        long size = databaseManager.getQuestionsSize();
        application.addQuestions(databaseManager);
        assertEquals(size + 17, databaseManager.getQuestionsSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
