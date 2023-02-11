package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.*;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestLogInWithFailedLogin {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private static final User user = new User("alex", "1234");

    @Before
    public void setUp() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
    }

    @Test(expected = NoSuchUserException.class)
    public void testLogInWithEmptyLogin() throws EmptyUsernameException, WrongPasswordException, NullFieldsException, NoSuchUserException, EmptyPasswordException {
        logInManager.logIn(user.getName(), user.getPassword());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
