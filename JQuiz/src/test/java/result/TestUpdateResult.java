package result;

import core.DatabaseManager;
import model.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestUpdateResult {
    private DatabaseManager databaseManager;
    private final Result result = new Result("vanya", new Date(), 70);

    @Before
    public void before() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addResult(result);
    }

    @Test
    public void updateResult() {
        Result newResult = new Result("vanya", new Date(), 50);
        databaseManager.updateResult(result, newResult);
        assertTrue(databaseManager.isExistResult(newResult));
        assertFalse(databaseManager.isExistResult(result));
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
