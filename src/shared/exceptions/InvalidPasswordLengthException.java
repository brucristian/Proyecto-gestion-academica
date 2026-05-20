package shared.exceptions;

public class InvalidPasswordLengthException extends ValidationException {
    public InvalidPasswordLengthException(String message) {
        super(message);
    }
}
