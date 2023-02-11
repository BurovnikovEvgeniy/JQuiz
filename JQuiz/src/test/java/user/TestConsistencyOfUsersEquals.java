package user;

import model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestConsistencyOfUsersEquals {
    @Test
    public void testConsistencyOfUsersEquals() {
        User user1 = new User("login", "password");
        User user2 = new User("login", "password");
        assertEquals(user1, user2);
        assertEquals(user1, user2);
    }
}
