package db;

import model.Results;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.io.Serializable;

public class ResultsSerializer implements Serializer<Results>, Serializable {
    @Override
    public void serialize(DataOutput2 out, Results results) throws IOException {
        out.writeUTF(results.getLogin());
        DATE.serialize(out, results.getDate());
        INTEGER.serialize(out, results.getScore());
    }

    @Override
    public Results deserialize(DataInput2 in, int i) throws IOException {

        return new Results(in.readUTF(), DATE.deserialize(in, i), INTEGER.deserialize(in, i));
    }
}
