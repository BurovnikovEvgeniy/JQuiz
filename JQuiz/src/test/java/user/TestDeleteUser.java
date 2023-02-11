package user;

import core.DatabaseManager;
import core.exceptions.UserAlreadyExistsException;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestDeleteUser {
    private DatabaseManager databaseManager;
    private final User preAddedUser = new User("vanya", "1234");

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addUser(preAddedUser);
    }

    @Test
    public void testDeleteUser() {
        long size = databaseManager.getUsersSize();
        String requiredLogin = preAddedUser.getName();
        databaseManager.deleteUser(requiredLogin);
        assertEquals(size - 1, databaseManager.getUsersSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
