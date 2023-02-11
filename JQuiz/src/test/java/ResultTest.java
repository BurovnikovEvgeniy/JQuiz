import core.DatabaseManager;
import core.QuestionManager;
import model.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

public class ResultTest {
    private DatabaseManager databaseManager;
    private Result result;

    @Before
    public void before() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
    }

    @Test
    public void testAddOneResult() {
        long size = databaseManager.getResultsSize();
        result = new Result("vanya", new Date(), 70);
        databaseManager.addResult(result);
        assertEquals(size + 1, databaseManager.getResultsSize());
    }

    @Test
    public void testAddManyResults() {
        long size = databaseManager.getResultsSize();

        databaseManager.addResult(new Result("vanya", new Date(), 80));
        databaseManager.addResult(new Result("petya", new Date(), 100));
        databaseManager.addResult(new Result("masha", new Date(), 120));

        assertEquals(size + 3, databaseManager.getResultsSize());
    }

    @Test
    public void findExistingResult() {
        assertTrue(databaseManager.isExistResult(result));
    }

    @Test
    public void findNotExistingResult() {
        Result requiredResult = new Result("kolya", new Date(), 20);
        assertFalse(databaseManager.isExistResult(requiredResult));
    }
}
