package question;

import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestFindExistingQuestion {
    private DatabaseManager databaseManager;
    private final Question preAddedQuestion = new Question("Which one?", new String[]{"one", "two", "three", "four"}, 2);

    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addQuestion(preAddedQuestion);
    }

    @Test
    public void testFindExistingQuestion() {
        String requiredQuestionText = preAddedQuestion.getQuestion();
        assertTrue(databaseManager.isExistQuestion(requiredQuestionText));
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
