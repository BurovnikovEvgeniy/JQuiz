package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.EmptyPasswordException;
import core.exceptions.EmptyUsernameException;
import core.exceptions.UserAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestUserSuccessRegistration {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String username = "maria";
    private final String password = "qwerty";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
    }

    @Test
    public void testUserSuccessRegistration() throws EmptyUsernameException, UserAlreadyExistsException, EmptyPasswordException {
        long size = databaseManager.getUsersSize();
        logInManager.register(username, password);
        assertEquals(size + 1, databaseManager.getUsersSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }

}
