package result;

import model.Result;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class TestTransitivityOfResultsEquals {
    @Test
    public void testTransitivityOfResultsEquals() {
        Date date = new Date();
        Result result1 = new Result("name", date, 10);
        Result result2 = new Result("name", date, 10);
        Result result3 = new Result("name", date, 10);
        assertTrue(!(result1.equals(result2) && result2.equals(result3)) || result1.equals(result3));
    }
}
