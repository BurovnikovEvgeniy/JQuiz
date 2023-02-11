package result;

import model.Result;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotEquals;

public class TestInequalityOfDifferentUsers {
    @Test
    public void testEqualityOfDifferentUsersWithSameFields() {
        Date date = new Date();
        Result result1 = new Result("name1", date, 10);
        Result result2 = new Result("name2", date, 10);
        assertNotEquals(result1, result2);
    }
}
