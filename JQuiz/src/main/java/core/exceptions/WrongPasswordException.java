package core.exceptions;

public class WrongPasswordException extends UserException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
