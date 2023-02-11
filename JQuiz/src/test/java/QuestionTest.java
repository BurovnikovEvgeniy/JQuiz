import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class QuestionTest {
    private DatabaseManager databaseManager;
    private QuestionManager questionManager;
    private final Question preAddedQuestion = new Question("Which one?", new String[]{"one", "two", "three", "four"}, 2);

    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        questionManager = new QuestionManager("db_test");
        databaseManager.addQuestion(preAddedQuestion);
    }

    @Test
    public void testAddOneQuestion() throws QuestionAlreadyExistsException {
        long size = databaseManager.getQuestionsSize();
        databaseManager.addQuestion(new Question("How many?", new String[]{"one", "two", "three", "four"}, 2));
        assertEquals(size + 1, databaseManager.getQuestionsSize());
    }

    @Test
    public void testAddManyQuestions() throws QuestionAlreadyExistsException {
        long size = databaseManager.getQuestionsSize();

        databaseManager.addQuestion(new Question("Question1?", new String[]{"one", "two", "three", "four"}, 2));
        databaseManager.addQuestion(new Question("Question2?", new String[]{"one", "two", "three", "four"}, 1));
        databaseManager.addQuestion(new Question("Question3?", new String[]{"one", "two", "three", "four"}, 4));
        databaseManager.addQuestion(new Question("Question4?", new String[]{"one", "two", "three", "four"}, 1));

        assertEquals(size + 4, databaseManager.getQuestionsSize());
    }

    @Test
    public void testFindExistingQuestion() throws QuestionAlreadyExistsException {
        String requiredQuestionText = preAddedQuestion.getQuestion();
        assertTrue(databaseManager.isExistQuestion(requiredQuestionText));
    }

    @Test
    public void testFindNotExistingQuestion() {
        String requiredQuestionText = "QuestionDoesNotExist?";
        assertFalse(databaseManager.isExistQuestion(requiredQuestionText));
    }

    @Test
    public void testDeleteQuestion() {
        long size = databaseManager.getQuestionsSize();
        String requiredQuestionText = preAddedQuestion.getQuestion();
        databaseManager.deleteQuestion(requiredQuestionText);
        assertEquals(size - 1, databaseManager.getQuestionsSize());
    }

    @Test
    public void testCountOfRandomQuestions() {
        Question[] allQuestions = questionManager.getAllQuestions();
        Question[] randomQuestions = questionManager.getTestQuestions(allQuestions.length - 1);
        assertEquals(randomQuestions.length, allQuestions.length - 1);
        randomQuestions = questionManager.getTestQuestions(allQuestions.length);
        assertEquals(randomQuestions.length, allQuestions.length);
        randomQuestions = questionManager.getTestQuestions(allQuestions.length + 1);
        assertEquals(randomQuestions.length, allQuestions.length);
    }

    @Test
    public void testIsDifferentRandomQuestions() {
        Question[] allQuestions = questionManager.getAllQuestions();
        Question[] randomQuestions = questionManager.getTestQuestions(allQuestions.length / 2);
        for (int i = 0; i < randomQuestions.length - 1; i++) {
            for (int j = i + 1; j < randomQuestions.length; j++) {
                assertNotEquals(randomQuestions[i], randomQuestions[j]);
            }
        }
    }
}
