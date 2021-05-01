package db;

import model.Question;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class QuestionDao extends BaseDao {
    private final DB questionsDB;
    private final List<Question> questions;

    public QuestionDao() {
        questionsDB = DBMaker.fileDB(new File(pathToDbs + "/questions.db")).make();
        questions = questionsDB.indexTreeList("questionsList", new QuestionSerializer()).createOrOpen();
    }

    public void close() {
        questionsDB.close();
    }

    public void addQuestion(Question newQuestion) {
        questions.add(newQuestion);
    }

    public Question findQuestion(String question) {
        for (Question question1 : questions) {
            if (question1.getQuestion().equals(question)) {
                return question1;
            }
        }
        return null;
    }

    public void updateQuestion(String question, Question updatedQuestion) {
        int i = findQuestionIndex(findQuestion(question));
        questions.remove(i);
        questions.add(i, updatedQuestion);
    }

    public void deleteQuestion(String question) {
        questions.remove(findQuestionIndex(findQuestion(question)));
    }

    public long getSize() {
        return questions.size();
    }

    private int findQuestionIndex(Question question) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestion().equals(question.getQuestion())
                    && Arrays.equals(questions.get(i).getAnswers(), question.getAnswers())
                    && questions.get(i).getCorrectAnswer() == question.getCorrectAnswer()) {
                return i;
            }
        }
        return -1;
    }

}
