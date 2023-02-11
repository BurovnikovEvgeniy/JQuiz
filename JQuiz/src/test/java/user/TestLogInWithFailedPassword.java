package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.*;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestLogInWithFailedPassword {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private static final User user = new User("alex", "1234");

    @Before
    public void setUp() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
        databaseManager.addUser(user);
    }

    @Test(expected = WrongPasswordException.class)
    public void testLogInWithEmptyLogin() throws EmptyUsernameException, WrongPasswordException, NullFieldsException, NoSuchUserException, EmptyPasswordException, WrongCredentialsSizeException {
        logInManager.logIn(user.getName(), "12345");
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
