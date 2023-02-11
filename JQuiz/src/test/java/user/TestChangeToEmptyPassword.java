package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.*;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestChangeToEmptyPassword {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String username = "anna";
    private final String password = "qwerty";
    private final String newPassword = "";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
        databaseManager.addUser(new User(username, password));
    }

    @Test (expected = EmptyPasswordException.class)
    public void testChangeToEmptyPassword() throws EmptyUsernameException, EmptyPasswordException {
        logInManager.changePassword(username, newPassword);
    }

    @After
    public void after() throws IOException {
        databaseManager.deleteDbDirectory();
    }
}
