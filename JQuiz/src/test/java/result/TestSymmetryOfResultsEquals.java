package result;

import model.Result;
import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.assertTrue;

public class TestSymmetryOfResultsEquals {
    @Test
    public void testSymmetryOfResultsEquals() {
        Date date = new Date();
        Result result1 = new Result("name1", date, 10);
        Result result2 = new Result("name2", date, 10);
        assertTrue((result1.equals(result2) && result2.equals(result1)) || (!result1.equals(result2) && !result2.equals(result1) ));
    }

}
