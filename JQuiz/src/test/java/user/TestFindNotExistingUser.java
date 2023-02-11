package user;

import core.DatabaseManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestFindNotExistingUser {
    private DatabaseManager databaseManager;

    @Before
    public void before() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
    }

    @Test
    public void findNotExistingUser() {
        String requiredUsername = "kolya";
        assertFalse(databaseManager.isExistUser(requiredUsername));
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
