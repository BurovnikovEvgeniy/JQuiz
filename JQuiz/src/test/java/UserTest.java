import core.DatabaseManager;
import core.exceptions.UserAlreadyExistsException;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class UserTest {
    private DatabaseManager databaseManager;

    @Before
    public void before() throws IOException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
    }

    @Test
    public void testAddOneUser() throws UserAlreadyExistsException {
        long size = databaseManager.getUsersSize();
        databaseManager.addUser(new User("vanya", "qwerty"));
        assertEquals(size + 1, databaseManager.getUsersSize());
    }

    @Test
    public void testAddManyUser() throws UserAlreadyExistsException {
        long size = databaseManager.getUsersSize();

        databaseManager.addUser(new User("petya", "1234"));
        databaseManager.addUser(new User("masha", "hrGS9SbWze"));

        assertEquals(size + 2, databaseManager.getUsersSize());
    }

    @Test
    public void testUpdateUser() {
        String requiredLogin = "vanya";
        User newUser = new User("vanya", "1234");
        databaseManager.updateUser(requiredLogin, newUser);
        assertEquals("1234", databaseManager.findUser("vanya").getPassword());
    }

    @Test
    public void testDeleteUser() {
        long size = databaseManager.getUsersSize();
        String requiredLogin = "vanya";
        databaseManager.deleteUser(requiredLogin);
        assertEquals(size - 1, databaseManager.getUsersSize());
    }

    @Test
    public void findExistingUser() {
        String requiredUsername = "vanya";
        assertTrue(databaseManager.isExistUser(requiredUsername));
    }

    @Test
    public void findNotExistingUser() {
        String requiredUsername = "kolya";
        assertFalse(databaseManager.isExistUser(requiredUsername));
    }
}
