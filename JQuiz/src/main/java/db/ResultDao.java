package db;

import model.Result;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.List;

public class ResultDao extends BaseDao {
    private final DB resultsDB;
    private final List<Result> results;

    public ResultDao() {
        resultsDB = DBMaker.fileDB(new File(pathToDbs + "/results.db")).make();
        results = resultsDB.indexTreeList("resultsList", new ResultSerializer()).createOrOpen();
    }

    public void close() {
        resultsDB.close();
    }

    public void addResult(Result newResult) {
        results.add(newResult);
    }

    public Result findResult(String name) {
        for (Result result : results) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    public void updateResult(String name, Result updatedResult) {
        int i = findResultIndex(findResult(name));
        results.remove(i);
        results.add(i, updatedResult);
    }

    public void deleteResult(String name) {
        results.remove(findResultIndex(findResult(name)));
    }

    public long getSize() {
        return results.size();
    }

    private int findResultIndex(Result result) {
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getName().equals(result.getName())
                    && results.get(i).getDate().equals(result.getDate())
                    && results.get(i).getScore() == result.getScore()) {
                return i;
            }
        }
        return -1;
    }


}
