package db;

import model.User;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.io.Serializable;

public class UserSerializer implements Serializer<User>, Serializable {
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
