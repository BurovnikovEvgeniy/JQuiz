package db;

import model.Question;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class QuestionDao extends BaseDao {
    private List<Question> questions;
    private DB questionsDB;

    public void addQuestion(Question newQuestion) {
        open();
        questions.add(newQuestion);
        close();
    }

    public Question findQuestion(String question) {
        open();
        for (Question question1 : questions) {
            if (question1.getQuestion().equals(question)) {
                close();
                return question1;
            }
        }
        close();
        return null;
    }

    public void updateQuestion(String question, Question updatedQuestion) {
        int i = findQuestionIndex(findQuestion(question));
        open();
        questions.remove(i);
        questions.add(i, updatedQuestion);
        close();
    }

    public void deleteQuestion(String question) {
        int i = findQuestionIndex(findQuestion(question));
        open();
        questions.remove(i);
        close();
    }

    public long getSize() {
        open();
        long size =  questions.size();
        close();
        return size;
    }

    public Question[] get(int n) {
        open();
        Question[] result = new Question[n];
        for (int i = 0; i < n; i++) {
            result[i] = questions.get(i);
        }
        close();
        return result;
    }

    public Question[] getAll() {
        open();
        Question[] result = new Question[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            result[i] = questions.get(i);
        }
        close();
        return result;
    }

    public boolean contains(Question question) {
        open();
        for (Question q : questions) {
            if (q.getQuestion().equals(question.getQuestion())) {
                close();
                return true;
            }
        }
        close();
        return false;
    }

    private int findQuestionIndex(Question question) {
        open();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestion().equals(question.getQuestion())
                    && Arrays.equals(questions.get(i).getAnswers(), question.getAnswers())
                    && questions.get(i).getCorrectAnswer() == question.getCorrectAnswer()) {
                close();
                return i;
            }
        }
        close();
        return -1;
    }

    private void open() {
        questionsDB = DBMaker.fileDB(new File(pathToDbs + "/questions.db"))
                .fileLockDisable()
                .fileMmapEnable()
                .closeOnJvmShutdown()
                .make();
        questions = questionsDB.indexTreeList("questionsList", new QuestionSerializer()).createOrOpen();
    }

    private void close() {
        questionsDB.close();
    }

}
