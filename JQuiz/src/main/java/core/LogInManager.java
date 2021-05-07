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
                    throw new RuntimeException("Неверный пароль!");
                }
            } else {
                throw new RuntimeException("Пользователь с таким именем не существует!");
            }
        } else {
            throw new RuntimeException("Вы заполнили не все поля!");
        }
    }

    public void register(String username, String password) throws RuntimeException {
        if (password.length() != 0 && username.length() != 0) {
            databaseManager.addUser(new User(username, password));
        } else {
            throw new RuntimeException("Вы заполнили не все поля!");
        }
    }

    public void registerAdmin(String username, String password) throws RuntimeException {
        register(username, password);
    }

    public boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin"); //todo change check
    }

    public void changePassword(String username, String password) {
        //todo add change
    }

    public User getAdmin() {
        return new User("admin", "admin"); //todo add call
    }
}
