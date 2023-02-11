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

public class TestAdminFailedRegistration {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String password = "";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
    }

    @Test (expected = EmptyPasswordException.class)
    public void testAdminFailedRegistration() throws EmptyUsernameException, UserAlreadyExistsException, EmptyPasswordException, WrongCredentialsSizeException {
        long size = databaseManager.getUsersSize();
        logInManager.registerAdmin(password);
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
