package user;

import model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEqualityOfDifferentUsersWithSameFields {
    @Test
    public void testEqualityOfDifferentUsersWithSameFields() {
        User user1 = new User("login", "password");
        User user2 = new User("login", "password");
        assertEquals(user1, user2);
    }
}
