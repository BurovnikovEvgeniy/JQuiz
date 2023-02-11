package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.EmptyPasswordException;
import core.exceptions.EmptyUsernameException;
import core.exceptions.UserAlreadyExistsException;
import core.exceptions.WrongCredentialsSizeException;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestFailedRegistrationUserAlreadyExists {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String username = "vitaliy";
    private final String password = "qwerty";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
        databaseManager.addUser(new User(username, password));
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testFailedRegistrationUserAlreadyExists() throws EmptyUsernameException, UserAlreadyExistsException, EmptyPasswordException, WrongCredentialsSizeException {
        logInManager.register(username, password);
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
