package question;

import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestCountOfRandomQuestionsLessThanAll {
    private DatabaseManager databaseManager;
    private QuestionManager questionManager;
    private final Question preAddedQuestion = new Question("Which one?", new String[]{"one", "two", "three", "four"}, 2);

    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        questionManager = new QuestionManager(databaseManager);
        databaseManager.createDbDirectory();
        databaseManager.addQuestion(preAddedQuestion);
    }

    @Test
    public void testCountOfRandomQuestionsAsAll() {
        Question[] allQuestions = questionManager.getAllQuestions();
        Question[] randomQuestions = questionManager.getTestQuestions(allQuestions.length - 1);
        assertEquals(randomQuestions.length, allQuestions.length - 1);
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
