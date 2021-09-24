package db;

import model.Result;
import org.mapdb.*;

import java.io.IOException;
import java.io.Serializable;

public class ResultDao extends BaseDao<Result> {
    private static final String DB_NAME = "/results.db";

    public ResultDao(String pathToDb) {
        super(pathToDb, DB_NAME, "resultsList", new ResultSerializer());
    }

    public void addResult(Result newResult) {
        open();
        entities.add(newResult);
        close();
    }

    public boolean isExistResult(Result result) {
        return findResultIndex(result) > -1;
    }

    public void updateResult(Result oldResult, Result updatedResult) {
        int i = findResultIndex(oldResult);
        open();
        entities.set(i, updatedResult);
        close();
    }

    public void deleteResult(Result result) {
        int i = findResultIndex(result);
        open();
        entities.remove(i);
        close();
    }

    public Result[] getAll() {
        open();
        Result[] result = new Result[0];
        result = entities.toArray(result);
        close();
        return result;
    }

    public boolean contains(Result result) {
        open();
        boolean contains = entities.contains(result);
        close();
        return contains;
    }

    private int findResultIndex(Result result) {
        open();
        int index = entities.indexOf(result);
        close();
        return index;
    }

    public static class ResultSerializer implements Serializer<Result>, Serializable {
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
}
