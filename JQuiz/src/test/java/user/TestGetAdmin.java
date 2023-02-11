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
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestGetAdmin {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String admin = "admin";
    private final String password = "qwerty";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
        databaseManager.addUser(new User(admin, password));
    }

    @Test
    public void testGetAdmin() {
        assertEquals(admin, logInManager.getAdmin().getName());
    }

    @After
    public void after() throws IOException {
        databaseManager.clearDb();
    }
}
