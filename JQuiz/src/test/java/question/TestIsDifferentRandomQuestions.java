package question;

import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

public class TestIsDifferentRandomQuestions {
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
    public void testIsDifferentRandomQuestions() {
        Question[] allQuestions = questionManager.getAllQuestions();
        Question[] randomQuestions = questionManager.getTestQuestions(allQuestions.length / 2);
        for (int i = 0; i < randomQuestions.length - 1; i++) {
            for (int j = i + 1; j < randomQuestions.length; j++) {
                assertNotEquals(randomQuestions[i], randomQuestions[j]);
            }
        }
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
