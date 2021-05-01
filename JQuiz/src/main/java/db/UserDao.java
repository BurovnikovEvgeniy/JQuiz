package db;

import model.User;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.List;

public class UserDao extends BaseDao {
    private final DB usersDB;
    private final List<User> users;

    public UserDao() {
        usersDB = DBMaker.fileDB(new File(pathToDbs + "/users.db")).make();
        users = usersDB.indexTreeList("usersList", new UserSerializer()).createOrOpen();
    }

    public void close() {
        usersDB.close();
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }

    public User findUser(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(String name, User updatedUser) {
        int i = findUserIndex(findUser(name));
        users.remove(i);
        users.add(i, updatedUser);
    }

    public void deleteUser(String name) {
        users.remove(findUserIndex(findUser(name)));
    }

    public long getSize() {
        return users.size();
    }

    private int findUserIndex(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(user.getName())
                    && users.get(i).getPassword().equals(user.getPassword())) {
                return i;
            }
        }
        return -1;
    }

}
