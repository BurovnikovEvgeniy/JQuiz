package user;

import core.DatabaseManager;
import core.exceptions.UserAlreadyExistsException;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestFindExistingUser {
    private DatabaseManager databaseManager;
    private final User preAddedUser = new User("preAddedUser1", "1234");

    @Before
    public void before() throws IOException, UserAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addUser(preAddedUser);
    }

    @Test
    public void findExistingUser() {
        String requiredUsername = preAddedUser.getName();
        assertTrue(databaseManager.isExistUser(requiredUsername));
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
