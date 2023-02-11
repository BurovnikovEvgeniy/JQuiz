package user;

import model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUserIsEqualToItself {
    @Test
    public void testUserIsEqualToItself() {
        User user = new User("login", "password");
        assertEquals(user, user);
    }
}
