package core;

import model.User;

public class LogInManager {

    DatabaseManager databaseManager;

    public LogInManager() {
        this.databaseManager = new DatabaseManager();
    }

    public void logIn(String username, String password) throws RuntimeException {
        if (password.length() != 0 && username.length() != 0) {
            User user = databaseManager.findUser(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    throw new RuntimeException("Wrong password! Try again!");
                }
            } else {
                throw new RuntimeException("User with this name does not exist. Try to register first");
            }
        } else {
            throw new RuntimeException("One or both fields are empty. Try again");
        }
    }

    public void register(String username, String password) throws RuntimeException {
        if (password.length() != 0 && username.length() != 0) {
            databaseManager.addUser(new User(username, password));
        } else {
            throw new RuntimeException("One or both fields are empty. Try again");
        }
    }

    public boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
