package core;

import model.Result;

public class ResultManager {

    private final DatabaseManager databaseManager;

    public ResultManager(String dbFolderName) {
        this.databaseManager = new DatabaseManager(dbFolderName);
    }

    public ResultManager(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }

    public void saveResults(Result result) {
        databaseManager.addResult(result);
    }

    public Result[] getAllResults() {
        return databaseManager.getAllResults();
    }

    public void deleteResult(Result result) {
        databaseManager.deleteResult(result);
    }
}
