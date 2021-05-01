package model;

import java.io.Serializable;

public class User implements Serializable {
    private final String name;
    private final String password;

    public User(String login, String password) {
        this.name = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
