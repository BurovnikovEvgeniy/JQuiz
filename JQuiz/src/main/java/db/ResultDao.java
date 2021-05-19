package db;

import model.Result;
import org.mapdb.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class ResultDao extends BaseDao {

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

    private List<Result> results;
    private DB resultsDB;
    private final String dbName = "/results.db";


    public ResultDao(String pathToDbs) {
        super(pathToDbs);
    }

    public String getDbName() {
        return dbName;
    }

    public void addResult(Result newResult) {
        open();
        results.add(newResult);
        close();
    }

    public Result findResult(String name) {
        open();
        Result result = null;
        for (Result r : results) {
            if (r.getName().equals(name)) {
                result = r;
                break;
            }
        }
        close();
        return result;
    }

    public void updateResult(String name, Result updatedResult) {
        int i = findResultIndex(findResult(name));
        open();
        results.set(i, updatedResult);
        close();
    }

    public void deleteResult(String name) {
        int i = findResultIndex(findResult(name));
        open();
        results.remove(i);
        close();
    }

    public long getSize() {
        open();
        long size = results.size();
        close();
        return size;
    }

    public Result[] getAll() {
        open();
        Result[] result = new Result[results.size()];
        for (int i = 0; i < results.size(); i++) {
            result[i] = results.get(i);
        }
        close();
        return result;
    }

    public boolean contains(Result result) {
        open();
        boolean contains = results.contains(result);
        close();
        return contains;
    }

    private int findResultIndex(Result result) {
        open();
        int index = results.indexOf(result);
        close();
        return index;
    }

    private void open() {
        resultsDB = DBMaker.fileDB(new File(pathToDbs + dbName))
                .fileLockDisable()
                .fileMmapEnable()
                .closeOnJvmShutdown()
                .make();
        results = resultsDB.indexTreeList("resultsList", new ResultSerializer()).createOrOpen();
    }

    private void close() {
        resultsDB.close();
    }

}
