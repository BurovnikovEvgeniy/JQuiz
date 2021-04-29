package core;

public class LogInManager {
    public boolean isValidEnteredData(String login, String password, String message) {
        return password.length() != 0 && login.length() != 0;
    }

    public boolean register(String login, String password, String message) {
        return password.length() != 0 && login.length() != 0;
    }

    public boolean isAdmin(String login, String password) {
        return login.equals("admin") && password.equals("admin");
    }
}
