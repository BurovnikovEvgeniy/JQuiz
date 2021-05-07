package db;

public abstract class BaseDao {
    protected static String pathToDbs;

    public BaseDao(String pathToDbs) {
        BaseDao.pathToDbs = pathToDbs;
    }
}
