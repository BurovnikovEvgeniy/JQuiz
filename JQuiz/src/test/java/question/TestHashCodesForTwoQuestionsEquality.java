package question;

import model.Question;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHashCodesForTwoQuestionsEquality {
        private final Question question1 = new Question("Which one?", new String[]{"one", "two", "three", "four"}, 2);
        private final Question question2 = new Question("Which one?", new String[]{"one", "two", "three", "four"}, 2);

        @Test
        public void testHashCodesForTwoQuestionsEquality()  {
            assertEquals(question1.hashCode(), question2.hashCode());
        }
}
