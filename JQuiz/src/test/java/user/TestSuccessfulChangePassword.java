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

import static org.junit.Assert.assertEquals;

public class TestSuccessfulChangePassword {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String username = "boris";
    private final String password = "qwerty";
    private final String newPassword = "1234";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
        databaseManager.addUser(new User(username, password));
    }

    @Test
    public void testSuccessfulChangePassword() throws EmptyUsernameException, EmptyPasswordException {
        logInManager.changePassword(username, newPassword);
        assertEquals(newPassword, databaseManager.findUser(username).getPassword());
    }

    @After
    public void after() throws IOException {
        databaseManager.deleteDbDirectory();
    }
}
