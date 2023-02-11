package question;

import core.Application;
import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestApplicationAddQuestion {
    private DatabaseManager databaseManager;
    private final String question = "Which one?";
    private final String answer1 = "one";
    private final String answer2 = "two";
    private final String answer3 = "three";
    private final String answer4 = "four";
    private final int correctIndex = 2;


    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
    }

    @Test
    public void testApplicationAddQuestion() throws QuestionAlreadyExistsException {
        long size = databaseManager.getQuestionsSize();
        Application.addQuestion(databaseManager, question, answer1, answer2, answer3, answer4, correctIndex);
        assertEquals(size + 1, databaseManager.getQuestionsSize());
    }

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
