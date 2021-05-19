package db;

import model.Question;
import org.mapdb.*;
import org.mapdb.serializer.SerializerArray;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class QuestionDao extends BaseDao {

    private static class QuestionSerializer implements Serializer<Question>, Serializable {
        @Override
        public void serialize(DataOutput2 out, Question question) throws IOException {
            out.writeUTF(question.getQuestion());
            SerializerArray<String> stringSerializerArray = new SerializerArray<>(Serializer.STRING, String.class);
            stringSerializerArray.serialize(out, question.getAnswers());
            out.writeInt(question.getCorrectAnswer());
        }

        @Override
        public Question deserialize(DataInput2 in, int i) throws IOException {
            String question = in.readUTF();
            SerializerArray<String> stringSerializerArray = new SerializerArray<>(Serializer.STRING, String.class);
            String[] variants = stringSerializerArray.deserialize(in, i);
            int index = in.readInt();

            return new Question(question, variants, index);
        }
    }

    private List<Question> questions;
    private DB questionsDB;
    private final String dbName = "/questions.db";

    public QuestionDao(String pathToDbs) {
        super(pathToDbs);
    }

    public String getDbName() {
        return dbName;
    }

    public void addQuestion(Question newQuestion) {
        open();
        questions.add(newQuestion);
        close();
    }

    public Question findQuestion(String question) {
        open();
        Question q = null;
        for (Question question1 : questions) {
            if (question1.getQuestion().equals(question)) {
                q = question1;
                break;
            }
        }
        close();
        return q;
    }

    public void updateQuestion(String question, Question updatedQuestion) {
        int i = findQuestionIndex(findQuestion(question));
        open();
        questions.set(i, updatedQuestion);
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
        long size = questions.size();
        close();
        return size;
    }

    public Question[] getAll() {
        open();
        Question[] result = new Question[0];
        result = questions.toArray(result);
        close();
        return result;
    }

    public boolean contains(Question question) {
        open();
        boolean contains = false;
        for (Question q : questions) {
            if (q.getQuestion().equals(question.getQuestion())) {
                contains = true;
                break;
            }
        }
        close();
        return contains;
    }

    private int findQuestionIndex(Question question) {
        open();
        int index = questions.indexOf(question);
        close();
        return index;
    }

    private void open() {
        questionsDB = DBMaker.fileDB(new File(pathToDbs + dbName))
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
