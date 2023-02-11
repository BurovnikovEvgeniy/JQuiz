package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.EmptyPasswordException;
import core.exceptions.EmptyUsernameException;
import core.exceptions.UserAlreadyExistsException;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestFailedRegistrationEmptyPassword {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String username = "sam";
    private final String password = "";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
    }

    @Test(expected = EmptyPasswordException.class)
    public void testFailedRegistrationUserAlreadyExists() throws EmptyUsernameException, UserAlreadyExistsException, EmptyPasswordException {
        logInManager.register(username, password);
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
