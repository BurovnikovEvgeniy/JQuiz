package question;

import model.Question;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class TestInequalityOfDifferentQuestions {
    @Test
    public void testInequalityOfDifferentQuestions() {
        String[] answers = {"answer1",  "answer2"};
        Question question1 = new Question("question1", answers, 0);
        Question question2 = new Question("question2", answers, 0);
        assertNotEquals(question1, question2);
    }
}
