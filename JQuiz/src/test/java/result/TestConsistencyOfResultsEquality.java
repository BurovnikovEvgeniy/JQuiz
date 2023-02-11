package result;

import model.Result;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestConsistencyOfResultsEquality {
    @Test
    public void testConsistencyOfResultsEquality() {
        Date date = new Date();
        Result result1 = new Result("name", date, 10);
        Result result2 = new Result("name", date, 10);
        assertEquals(result1.equals(result2), result2.equals(result1));
    }
}
