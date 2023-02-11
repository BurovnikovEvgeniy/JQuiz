package user;

import model.User;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class TestInequalityOfUserAndNull {
    @Test
    public void testInequalityOfUserAndNull() {
        User user1 = new User("login", "password");
        User user2 = null;
        assertNotEquals(user1, user2);
    }
}
