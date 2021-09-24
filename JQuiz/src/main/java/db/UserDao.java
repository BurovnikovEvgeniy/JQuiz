package db;

import model.User;
import org.mapdb.*;

import java.io.IOException;
import java.io.Serializable;

public class UserDao extends BaseDao<User> {
    private static final String DB_NAME = "/users.db";

    public UserDao(String pathToDb) {
        super(pathToDb, DB_NAME, "usersList", new UserSerializer());
    }

    public void addUser(User newUser) {
        open();
        entities.add(newUser);
        close();

    }

    public User findUser(String name) {
        open();
        User user = null;
        for (User u : entities) {
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
        entities.set(i, updatedUser);
        close();
    }

    public void deleteUser(String name) {
        int i = findUserIndex(findUser(name));
        open();
        entities.remove(i);
        close();
    }

    public boolean contains(String username) {
        open();
        boolean contains = false;
        for (User u : entities) {
            if (u.getName().equals(username)) {
                contains = true;
                break;
            }
        }
        close();
        return contains;
    }

    private int findUserIndex(User user) {
        open();
        int index = entities.indexOf(user);
        close();
        return index;
    }

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
}
