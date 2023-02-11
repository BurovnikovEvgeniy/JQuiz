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

public class TestRegisterWithMoreThanMaxSizePassword {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String username = "aaaaa";
    private final String password = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
    }

    @Test(expected = WrongCredentialsSizeException.class)
    public void testFailedRegistrationUserAlreadyExists() throws EmptyUsernameException, UserAlreadyExistsException, EmptyPasswordException, WrongCredentialsSizeException {
        logInManager.register(username, password);
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
