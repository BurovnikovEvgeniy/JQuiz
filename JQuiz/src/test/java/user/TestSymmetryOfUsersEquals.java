package user;

import model.User;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestSymmetryOfUsersEquals {
    @Test
    public void testSymmetryOfUsersEquals() {
        User user1 = new User("login", "password");
        User user2 = new User("login", "password");
        assertTrue((user1.equals(user2) && user1.equals(user2)) || (!user1.equals(user2) && !user1.equals(user2) ));
    }
}
