package user;

import java.io.IOException;

import core.exceptions.EmptyPasswordException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.DatabaseManager;
import core.exceptions.UserAlreadyExistsException;
import model.User;

public class TestAddExistUser {

    private DatabaseManager databaseManager;
    private static final User user = new User("petya", "1234");

    @Before
    public void setUp() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addUser(user);
    }

    @Test (expected = UserAlreadyExistsException.class)
    public void testAddExistUser() throws UserAlreadyExistsException {
        databaseManager.addUser(user);
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
