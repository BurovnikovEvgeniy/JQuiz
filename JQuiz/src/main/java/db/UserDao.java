package db;

import model.User;
import org.mapdb.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class UserDao extends BaseDao {

    public static class UserSerializer implements Serializer<User>, Serializable {
        @Override
        public void serialize(DataOutput2 out, User user) throws IOException {
            out.writeUTF(user.getName());
            out.writeUTF(user.getPassword());
        }

        @Override
        public User deserialize(DataInput2 in, int i) throws IOException {
            return new User(in.readUTF(), in.readUTF());
        }
    }

    private DB usersDB;
    private List<User> users;
    private final String dbName = "/users.db";


    public UserDao(String pathToDbs) {
        super(pathToDbs);
    }

    public String getDbName() {
        return dbName;
    }

    public void addUser(User newUser) {
        open();
        users.add(newUser);
        close();

    }

    public User findUser(String name) {
        open();
        User user = null;
        for (User u : users) {
            if (u.getName().equals(name)) {
                user = u;
                break;
            }
        }
        close();
        return user;
    }

    public void updateUser(String name, User updatedUser) {
        int i = findUserIndex(findUser(name));
        open();
        users.set(i, updatedUser);
        close();
    }

    public void deleteUser(String name) {
        int i = findUserIndex(findUser(name));
        open();
        users.remove(i);
        close();
    }

    public long getSize() {
        open();
        long size = users.size();
        close();
        return size;
    }

    public boolean contains(User user) {
        open();
        boolean contains = false;
        for (User u : users) {
            if (u.getName().equals(user.getName())) {
                contains = true;
                break;
            }
        }
        close();
        return contains;
    }

    private int findUserIndex(User user) {
        open();
        int index = users.indexOf(user);
        close();
        return index;
    }

    private void open() {
        usersDB = DBMaker.fileDB(new File(pathToDbs + dbName))
                .fileLockDisable()
                .fileMmapEnable()
                .checksumHeaderBypass()
                .closeOnJvmShutdown()
                .make();
        users = usersDB.indexTreeList("usersList", new UserSerializer()).createOrOpen();
    }

    private void close() {
        usersDB.close();
    }


}
