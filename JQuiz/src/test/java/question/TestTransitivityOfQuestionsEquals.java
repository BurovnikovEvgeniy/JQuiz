package question;

import model.Question;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTransitivityOfQuestionsEquals {
    @Test
    public void testSymmetryOfQuestionsEquals() {
        String[] answers = {"answer1",  "answer2"};
        Question question1 = new Question("question", answers, 0);
        Question question2 = new Question("question", answers, 0);
        Question question3 = new Question("question", answers, 0);
        assertTrue(!(question1.equals(question2) && question2.equals(question3)) || question1.equals(question3));
    }
}
