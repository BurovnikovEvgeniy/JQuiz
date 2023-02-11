package result;

import core.DatabaseManager;
import model.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestAddManyResults {
    private DatabaseManager databaseManager;
    private Result result;

    @Before
    public void before() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
    }

    @Test
    public void testAddManyResults() {
        long size = databaseManager.getResultsSize();

        databaseManager.addResult(new Result("vanya", new Date(), 80));
        databaseManager.addResult(new Result("petya", new Date(), 100));
        databaseManager.addResult(new Result("masha", new Date(), 120));

        assertEquals(size + 3, databaseManager.getResultsSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
