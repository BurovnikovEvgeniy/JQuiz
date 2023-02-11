package result;

import core.DatabaseManager;
import core.ResultManager;
import model.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertArrayEquals;

public class TestGetAllResults {
    private DatabaseManager databaseManager;
    private ResultManager resultManager;
    private Result result;

    @Before
    public void before() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        resultManager = new ResultManager(databaseManager);
    }

    @Test
    public void testAddManyResults() {
        Result[] results = new Result[3];
        Result vanya = new Result("vanya", new Date(), 80);
        Result petya = new Result("petya", new Date(), 100);
        Result masha = new Result("masha", new Date(), 120);
        results[0] = vanya;
        results[1] = petya;
        results[2] = masha;
        resultManager.saveResults(vanya);
        resultManager.saveResults(petya);
        resultManager.saveResults(masha);
        assertArrayEquals(results, resultManager.getAllResults());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }


}
