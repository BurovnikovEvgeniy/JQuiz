package question;

import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestCountOfReturnedRandomIndexesEqualsNeeded {
    private DatabaseManager databaseManager;
    private QuestionManager questionManager;
    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        questionManager = new QuestionManager(databaseManager);
        databaseManager.createDbDirectory();
    }
    @Test
    public void testCountOfReturnedRandomIndexesEqualsNeeded() {
        int count = 4;
        int max = 5;
        int[] results = questionManager.getRandomIndexes(count, max);
        assertEquals(count, results.length);
    }
    @After
    public void after() {
        databaseManager.clearDb();
    }
}
