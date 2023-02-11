package question;

import model.Question;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class TestHashCodesForOneQuestionEquality {
    private final Question question = new Question("Which one?", new String[]{"one", "two", "three", "four"}, 2);

    @Test
    public void testHashCodesForOneQuestionEquality()  {
        assertEquals(question.hashCode(), question.hashCode());
    }
}
