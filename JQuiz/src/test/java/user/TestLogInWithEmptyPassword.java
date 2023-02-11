package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.*;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestLogInWithEmptyPassword {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private static final User user = new User("petya", "1234");

    @Before
    public void setUp() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
    }

    @Test(expected = EmptyPasswordException.class)
    public void testLogInWithEmptyPassword() throws EmptyUsernameException, WrongPasswordException, NullFieldsException, NoSuchUserException, EmptyPasswordException {
        logInManager.logIn(user.getName(), "");
    }
    @Test (expected = NullFieldsException.class)
    public void testLogInWithNullPassword() throws EmptyUsernameException, WrongPasswordException, NullFieldsException, NoSuchUserException, EmptyPasswordException {
        logInManager.logIn(user.getName(), null);
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
