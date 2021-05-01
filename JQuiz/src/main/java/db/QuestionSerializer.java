package db;

import model.Question;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;
import org.mapdb.serializer.SerializerArray;

import java.io.IOException;
import java.io.Serializable;

public class QuestionSerializer implements Serializer<Question>, Serializable {
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
