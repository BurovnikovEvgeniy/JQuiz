package user;

import core.DatabaseManager;
import core.exceptions.UserAlreadyExistsException;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestAddManyUsers {
    private DatabaseManager databaseManager;

    @Before
    public void before() throws IOException {

        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
    }

    @Test
    public void testAddManyUser() throws UserAlreadyExistsException {
        long size = databaseManager.getUsersSize();

        databaseManager.addUser(new User("petyaa", "1234"));
        databaseManager.addUser(new User("mashaa", "hrGS9SbWze"));

        assertEquals(size + 2, databaseManager.getUsersSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
