package db;

import model.Result;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ResultDao extends BaseDao {
    private List<Result> results;
    private DB resultsDB;

    public void addResult(Result newResult) {
        open();
        results.add(newResult);
        close();
    }

    public Result findResult(String name) {
        open();
        for (Result result : results) {
            if (result.getName().equals(name)) {
                close();
                return result;
            }
        }
        close();
        return null;
    }

    public Result[] findAllResults(String name) {
        open();
        ArrayList<Result> resultArrayList = new ArrayList<>();
        for (Result result : results) {
            if (result.getName().equals(name)) {
                resultArrayList.add(result);
            }
        }
        close();
        return (Result[]) resultArrayList.toArray();
    }

    public void updateResult(String name, Result updatedResult) {
        int i = findResultIndex(findResult(name));
        open();
        results.remove(i);
        results.add(i, updatedResult);
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
        boolean doesContain = results.contains(result);
        close();
        return doesContain;
    }

    private int findResultIndex(Result result) {
        open();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getName().equals(result.getName())
                    && results.get(i).getDate().equals(result.getDate())
                    && results.get(i).getScore() == result.getScore()) {
                close();
                return i;
            }
        }
        close();
        return -1;
    }

    private void open() {
        resultsDB = DBMaker.fileDB(new File(pathToDbs + "/results.db"))
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
