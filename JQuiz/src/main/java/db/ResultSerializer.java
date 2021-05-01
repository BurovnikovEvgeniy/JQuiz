package db;

import model.Result;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.io.Serializable;

public class ResultSerializer implements Serializer<Result>, Serializable {
    @Override
    public void serialize(DataOutput2 out, Result result) throws IOException {
        out.writeUTF(result.getName());
        DATE.serialize(out, result.getDate());
        INTEGER.serialize(out, result.getScore());
    }

    @Override
    public Result deserialize(DataInput2 in, int i) throws IOException {

        return new Result(in.readUTF(), DATE.deserialize(in, i), INTEGER.deserialize(in, i));
    }
}
