package question;

import model.Question;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestConsistencyOfQuestionsEquals {
    @Test
    public void testConsistencyOfQuestionsEquals() {
        String[] answers = {"answer1",  "answer2"};
        Question question1 = new Question("question", answers, 0);
        Question question2 = new Question("question", answers, 0);
        assertEquals(question1.equals(question2), question1.equals(question2));
    }
}
