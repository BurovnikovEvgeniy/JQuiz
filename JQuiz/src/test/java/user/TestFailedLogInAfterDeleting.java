package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.*;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestFailedLogInAfterDeleting {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private static final User user = new User("alexa", "1234");

    @Before
    public void setUp() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
        databaseManager.addUser(user);
    }

    @Test(expected = NoSuchUserException.class)
    public void testSuccessfulLogIn() throws EmptyUsernameException, WrongPasswordException, NullFieldsException, NoSuchUserException, EmptyPasswordException {
        databaseManager.deleteUser(user.getName());
        logInManager.logIn(user.getName(), user.getPassword());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
