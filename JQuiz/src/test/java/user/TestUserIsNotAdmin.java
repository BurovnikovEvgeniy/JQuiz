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

public class TestUserIsNotAdmin {
    private DatabaseManager databaseManager;
    private LogInManager logInManager;
    private final String admin = "admin";
    private final String password = "qwerty";
    private final String username = "misha";

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        logInManager = new LogInManager(databaseManager);
        databaseManager.createDbDirectory();
        databaseManager.addUser(new User(admin, password));
    }

    @Test
    public void testUserIsNotAdmin() {
        assertEquals(false, logInManager.isAdmin(username, password));
    }

    @After
    public void after() throws IOException {
        databaseManager.clearDb();
    }
}