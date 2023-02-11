package user;

import model.User;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTransitivityOfUsersEquals {
    @Test
    public void testTransitivityOfUsersEquals() {
        User user1 = new User("login", "password");
        User user2 = new User("login", "password");
        User user3 = new User("login", "password");
        assertTrue(!(user1.equals(user2) && user2.equals(user3)) || user1.equals(user3));
    }
}
