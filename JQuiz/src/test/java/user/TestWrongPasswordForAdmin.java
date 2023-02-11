package user;

import core.DatabaseManager;
import core.LogInManager;
import core.exceptions.UserAlreadyExistsException;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestWrongPasswordForAdmin {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String admin = "admin";
    private final String password = "qwerty";
    private final String wrongPassword = "qwrty";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
        databaseManager.addUser(new User(admin, password));
    }

    @Test
    public void testWrongPasswordForAdmin() {
        assertEquals(false, logInManager.isAdmin(admin, wrongPassword));
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }

}
