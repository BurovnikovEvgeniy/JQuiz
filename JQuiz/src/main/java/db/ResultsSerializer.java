package db;

import model.Result;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.io.Serializable;

public class ResultsSerializer implements Serializer<Result>, Serializable {
    @Override
    public void serialize(DataOutput2 out, Result results) throws IOException {
        out.writeUTF(results.getLogin());
        DATE.serialize(out, results.getDate());
        INTEGER.serialize(out, results.getScore());
    }

    @Override
    public Result deserialize(DataInput2 in, int i) throws IOException {

        return new Result(in.readUTF(), DATE.deserialize(in, i), INTEGER.deserialize(in, i));
    }
}
