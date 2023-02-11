package result;

import model.Result;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotEquals;

public class TestInequalityOfResultAndNull {
    @Test
    public void testInequalityOfResultAndNull() {
        Date date = new Date();
        Result result = new Result("name", date, 10);
        assertNotEquals(result, null);
    }
}
