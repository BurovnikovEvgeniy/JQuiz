package result;

import core.DatabaseManager;
import model.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestAddOneResult {
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

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
