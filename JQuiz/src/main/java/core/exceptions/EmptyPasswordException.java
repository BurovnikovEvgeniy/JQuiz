package core.exceptions;

public class EmptyPasswordException extends WrongPasswordException {
    public EmptyPasswordException(String message) {
        super(message);
    }
}
