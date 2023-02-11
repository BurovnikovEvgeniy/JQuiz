package user;

import core.DatabaseManager;
import core.exceptions.UserAlreadyExistsException;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestUpdateUser {
    private DatabaseManager databaseManager;
    private final User preAddedUser = new User("vanya", "12345");

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addUser(preAddedUser);
    }

    @Test
    public void testUpdateUser() {
        String requiredLogin = preAddedUser.getName();
        String newPassword = "123";
        User newUser = new User(requiredLogin, newPassword);
        databaseManager.updateUser(requiredLogin, newUser);
        assertEquals(newPassword, databaseManager.findUser(requiredLogin).getPassword());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
