package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseDao {
    protected final String pathToDbs = System.getProperty("user.home") + "/JQuiz/db";

    public BaseDao() {
        try {
            Files.createDirectories(Paths.get(pathToDbs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
