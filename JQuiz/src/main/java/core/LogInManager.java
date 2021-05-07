package core;

import core.exceptions.*;
import model.User;

public class LogInManager {

    private final DatabaseManager databaseManager;

    public LogInManager() {
        this.databaseManager = new DatabaseManager();
    }

    public void logIn(
            String username,
            String password
    ) throws NullFieldsException, NoSuchUserException, WrongPasswordException {
        if (username == null || password == null) {
            throw new NullFieldsException("Имя пользователя или пароль принимают значение null");
        }

        checkEmptyFields(username, password);

        User user = databaseManager.findUser(username);

        if (user == null) {
            throw new NoSuchUserException("Пользователь с таким именем не существует!");
        }

        if (!user.getPassword().equals(password)) {
            throw new WrongPasswordException("Неверный пароль!");
        }
    }

    public void register(String username, String password) throws RuntimeException {
        checkEmptyFields(username, password);
        databaseManager.addUser(new User(username, password));
    }

    public void registerAdmin(String username, String password) throws RuntimeException {
        register(username, password);
    }

    public boolean isAdmin(String username, String password) {
        if (!username.equals("admin")) return false;
        User admin = databaseManager.findUser(username);
        return password.equals(admin.getPassword());
    }

    public void changePassword(String username, String password) throws EmptyUsernameException, EmptyPasswordException {
        checkEmptyFields(username, password);
        databaseManager.updateUser(username, new User(username, password));
    }

    public User getAdmin() {
        return databaseManager.findUser("admin");
    }

    private void checkEmptyFields(String username, String password) {
        if (username.isEmpty()) {
            throw new EmptyUsernameException("Введите имя пользователя!");
        }

        if (password.isEmpty()) {
            throw new EmptyPasswordException("Введите пароль!");
        }
    }
}
