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

import static org.junit.Assert.assertEquals;

public class TestAdminSuccessRegistration {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String password = "qwerty";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
    }

    @Test
    public void testAdminSuccessRegistration() throws EmptyUsernameException, UserAlreadyExistsException, EmptyPasswordException {
        long size = databaseManager.getUsersSize();
        logInManager.registerAdmin(password);
        assertEquals(size + 1, databaseManager.getUsersSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
