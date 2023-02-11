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

public class TestFindNotExistingResult {
    private DatabaseManager databaseManager;
    private Result result;

    @Before
    public void before() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        result = new Result("vanya", new Date(), 70);
    }

    @Test
    public void findNotExistingResult() {
        Result requiredResult = new Result("kolya", new Date(), 20);
        assertFalse(databaseManager.isExistResult(requiredResult));
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
