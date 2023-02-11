package question;

import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

public class TestMaxOfReturnedRandomIndexesEqualsNeeded {
    private DatabaseManager databaseManager;
    private QuestionManager questionManager;
    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        questionManager = new QuestionManager(databaseManager);
        databaseManager.createDbDirectory();
    }
    @Test
    public void testMaxOfReturnedRandomIndexesEqualsNeeded() {
        int count = 4;
        int max = 6;
        int[] results = questionManager.getRandomIndexes(count, max);
        assertTrue(IntStream.of(results).max().getAsInt() <= max);
    }
    @After
    public void after() {
        databaseManager.clearDb();
    }
}
