package question;

import model.Question;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestQuestionIsEqualToItself {
    @Test
    public void testQuestionIsEqualToItself() {
        String[] answers = {"answer1",  "answer2"};
        Question question = new Question("question1", answers, 0);
        assertEquals(question, question);
    }
}
