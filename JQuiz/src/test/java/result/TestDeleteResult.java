package result;

import core.DatabaseManager;
import core.ResultManager;
import model.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestDeleteResult {
    private DatabaseManager databaseManager;
    private ResultManager resultManager;
    private final Result result = new Result("vanya", new Date(), 70);

    @Before
    public void before() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        resultManager = new ResultManager(databaseManager);
        databaseManager.addResult(result);
    }

    @Test
    public void testDeleteResult() {
        long size = databaseManager.getResultsSize();
        resultManager.deleteResult(result);
        assertEquals(size - 1, databaseManager.getResultsSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
