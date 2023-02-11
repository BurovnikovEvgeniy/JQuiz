package question;

import core.DatabaseManager;
import core.QuestionManager;
import core.exceptions.QuestionAlreadyExistsException;
import core.exceptions.UserAlreadyExistsException;
import model.Question;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestAddManyQuestions {
    private DatabaseManager databaseManager;
    private final Question preAddedQuestion = new Question("Which one?", new String[]{"one", "two", "three", "four"}, 2);

    @Before
    public void before() throws IOException, QuestionAlreadyExistsException {
        databaseManager = new DatabaseManager("db_test");
        databaseManager.createDbDirectory();
        databaseManager.addQuestion(preAddedQuestion);
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

    @After
    public void after() {
        databaseManager.clearDb();
    }
}
