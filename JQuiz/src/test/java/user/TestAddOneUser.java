package user;

import core.DatabaseManager;
import core.exceptions.UserAlreadyExistsException;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestAddOneUser {
    private DatabaseManager databaseManager;

    @Before
    public void before() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
    }

    @Test
    public void testAddOneUser() throws UserAlreadyExistsException {
        long size = databaseManager.getUsersSize();
        databaseManager.addUser(new User("vanya", "qwerty"));
        assertEquals(size + 1, databaseManager.getUsersSize());
    }

    @Test
    public void after() throws IOException {
        databaseManager.deleteDbDirectory();
    }
}
