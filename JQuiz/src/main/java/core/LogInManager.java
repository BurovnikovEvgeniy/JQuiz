package core;

import core.exceptions.*;
import model.User;

public class LogInManager {

    private final DatabaseManager databaseManager;
    private final static int MIN_USERNAME_SIZE = 3;
    private final static int MIN_PASSWORD_SIZE = 3;
    private final static int MAX_USERNAME_SIZE = 20;
    private final static int MAX_PASSWORD_SIZE = 20;

    public LogInManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public User logIn(
            String username,
            String password
    ) throws NullFieldsException, NoSuchUserException, WrongPasswordException, EmptyUsernameException, EmptyPasswordException, WrongCredentialsSizeException {
        if (username == null || password == null) {
            throw new NullFieldsException("Имя пользователя или пароль принимают значение null");
        }

        checkCredentials(username, password);

        User user = databaseManager.findUser(username);

        if (user == null) {
            throw new NoSuchUserException("Пользователь с таким именем не существует!");
        }

        if (!user.getPassword().equals(password)) {
            throw new WrongPasswordException("Неверный пароль!");
        }

        return user;
    }

    public void register(String username, String password) throws EmptyUsernameException, EmptyPasswordException, UserAlreadyExistsException, WrongCredentialsSizeException {
        checkCredentials(username, password);
        databaseManager.addUser(new User(username, password));
    }

    public void registerAdmin(String password) throws EmptyUsernameException, EmptyPasswordException, UserAlreadyExistsException, WrongCredentialsSizeException {
        register("admin", password);
    }

    public boolean isAdmin(String username, String password) {
        if (!username.equals("admin")) {
            return false;
        }
        User admin = databaseManager.findUser(username);
        return password.equals(admin.getPassword());
    }

    public void changePassword(String username, String password) throws EmptyUsernameException, EmptyPasswordException, WrongCredentialsSizeException {
        checkCredentials(username, password);
        databaseManager.updateUser(username, new User(username, password));
    }

    public User getAdmin() {
        return databaseManager.findUser("admin");
    }

    private void checkCredentials(String username, String password) throws EmptyUsernameException, EmptyPasswordException, WrongCredentialsSizeException {
        if (username.isEmpty()) {
            throw new EmptyUsernameException("Введите имя пользователя!");
        }

        if (password.isEmpty()) {
            throw new EmptyPasswordException("Введите пароль!");
        }

        if (username.length() < MIN_USERNAME_SIZE) {
            throw new WrongCredentialsSizeException("Логин слишком маленький!");
        }

        if (username.length() > MAX_USERNAME_SIZE) {
            throw new WrongCredentialsSizeException("Логин слишком большой!");
        }

        if (password.length() < MIN_PASSWORD_SIZE) {
            throw new WrongCredentialsSizeException("Пароль слишком маленький!");
        }

        if (password.length() > MAX_PASSWORD_SIZE) {
            throw new WrongCredentialsSizeException("Пароль слишком большой!");
        }
    }
}
