package db;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.File;
import java.util.List;

public abstract class BaseDao<T> {
    private final String pathToDb;
    private final String dbName;
    private final String treeName;
    private final Serializer<T> serializer;
    private DB db;
    protected List<T> entities;

    public BaseDao(String pathToDb, String dbName, String treeName, Serializer<T> serializer) {
        this.pathToDb = pathToDb;
        this.dbName = dbName;
        this.treeName = treeName;
        this.serializer = serializer;
    }

    public long getSize() {
        open();
        long size = entities.size();
        close();
        return size;
    }

    public String getDbName() {
        return dbName;
    }

    protected void open() {
        db = DBMaker.fileDB(new File(pathToDb + dbName))
                .fileLockDisable()
                .fileMmapEnable()
                .checksumHeaderBypass()
                .closeOnJvmShutdown()
                .make();
        entities = db.indexTreeList(treeName, serializer).createOrOpen();
    }

    protected void close() {
        db.close();
    }
}
