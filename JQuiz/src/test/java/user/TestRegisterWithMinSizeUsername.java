package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.EmptyPasswordException;
import core.exceptions.EmptyUsernameException;
import core.exceptions.UserAlreadyExistsException;
import core.exceptions.WrongCredentialsSizeException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestRegisterWithMinSizeUsername {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String username = "aaa";
    private final String password = "aaaaaaaaaa";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
    }

    @Test
    public void testFailedRegistrationUserAlreadyExists() throws EmptyUsernameException, UserAlreadyExistsException, EmptyPasswordException, WrongCredentialsSizeException {
        long dbSizeBeforeRegistration = databaseManager.getUsersSize();
        logInManager.register(username, password);
        assertEquals(dbSizeBeforeRegistration + 1, databaseManager.getUsersSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
