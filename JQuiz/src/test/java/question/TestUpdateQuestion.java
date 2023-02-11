package question;

import core.DatabaseManager;
import core.LogInManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class TestUpdateQuestion {
    private DatabaseManager databaseManager;
    private QuestionManager questionManager;
    private final Question preAddedQuestion = new Question("Which one?", new String[]{"one", "two", "three", "four"}, 2);

    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        questionManager = new QuestionManager(databaseManager);
        databaseManager.addQuestion(preAddedQuestion);
    }

    @Test
    public void testUpdateQuestion() {
        Question newQuestion = new Question("How many?", new String[]{"one", "two", "three", "four"}, 3);
        questionManager.updateQuestion(preAddedQuestion, newQuestion);
        assertEquals(3, databaseManager.findQuestion(newQuestion.getQuestion()).getCorrectAnswer());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
