package core.exceptions;

public class EmptyUsernameException extends WrongUsernameException {
    public EmptyUsernameException(String message) {
        super(message);
    }
}
