package result;

import model.Result;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestValueOfScoreAfterCreatingResult {
    @Test
    public void testValueOfScoreAfterCreatingResult() {
        boolean[] answers = {false, true, true, true, false, true};
        Date date = new Date();
        Result result = new Result("name", date, answers);
        assertEquals(result.getScore(), 4);
    }
}
