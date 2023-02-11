package user;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.EmptyPasswordException;
import core.exceptions.UserAlreadyExistsException;
import model.User;

public class TestLogInWithEmptyLogin {

    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private static final User user = new User("petya", "1234");

    @Before
    public void setUp() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addUser(user);
//        logInManager = new LogInManager()
    }

    @Test
    public void testLogInWithEmptyLogin() {
        Assert.assertThrows(EmptyPasswordException.class, () -> logInManager.logIn(user.getName(), ""));
    }
    @Test
    public void testLogInWithNullLogin() {
        Assert.assertThrows(EmptyPasswordException.class, () -> logInManager.logIn(user.getName(), null));
    }

    @After
    public void after() throws IOException {
        databaseManager.deleteDbDirectory();
    }

}
