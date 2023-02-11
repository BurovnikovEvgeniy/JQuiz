package user;

import java.io.IOException;

import core.exceptions.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.DatabaseManager;
import core.LogInManager;
import model.User;

public class TestLogInWithEmptyLogin {

    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private static final User user = new User("petya", "1234");

    @Before
    public void setUp() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
    }

    @Test (expected = EmptyUsernameException.class)
    public void testLogInWithEmptyLogin() throws EmptyUsernameException, WrongPasswordException, NullFieldsException, NoSuchUserException, EmptyPasswordException, WrongCredentialsSizeException {
        logInManager.logIn("", user.getPassword());
    }
    @Test (expected = NullFieldsException.class)
    public void testLogInWithNullLogin() throws EmptyUsernameException, WrongPasswordException, NullFieldsException, NoSuchUserException, EmptyPasswordException, WrongCredentialsSizeException {
        logInManager.logIn(null, user.getPassword());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }

}
