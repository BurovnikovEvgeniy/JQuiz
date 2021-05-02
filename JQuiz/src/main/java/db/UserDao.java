package db;

import model.User;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.List;

public class UserDao extends BaseDao {
    private DB usersDB;
    private List<User> users;

    public void addUser(User newUser) {
        open();
        users.add(newUser);
        close();

    }

    public User findUser(String name) {
        open();
        for (User user : users) {
            if (user.getName().equals(name)) {
                close();
                return user;
            }
        }
        close();
        return null;
    }

    public void updateUser(String name, User updatedUser) {
        int i = findUserIndex(findUser(name));
        open();
        users.remove(i);
        users.add(i, updatedUser);
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
        for (User u : users) {
            if (u.getName().equals(user.getName())) {
                close();
                return true;
            }
        }
        close();
        return false;
    }

    private int findUserIndex(User user) {
        open();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(user.getName())
                    && users.get(i).getPassword().equals(user.getPassword())) {
                close();
                return i;
            }
        }
        close();
        return -1;
    }

    private void open() {
        usersDB = DBMaker.fileDB(new File(pathToDbs + "/users.db"))
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
