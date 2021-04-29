package core;

import model.Result;

import java.util.Date;

public class ResultManager {
    public void saveResults(Result results) {

    }

    public Result[] getAllResults() {
        Result[] r = new Result[5];
        boolean[] answers = new boolean[]{true, false, true, true, false, true, true, true, true, true};
        for (int i = 0; i < 5; i++) {
            r[i] = new Result("admin", new Date(), answers);
        }
        return r;
    }
}
