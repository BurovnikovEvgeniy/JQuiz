package user;

import model.User;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class TestInequalityOfDifferentUsers {
    @Test
    public void testInequalityOfUserAndNull() {
        User user1 = new User("login", "password");
        User user2 = new User("login2", "password2");
        assertNotEquals(user1, user2);
    }
}
