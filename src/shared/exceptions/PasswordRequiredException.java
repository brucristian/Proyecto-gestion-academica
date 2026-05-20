package shared.exceptions;

public class PasswordRequiredException extends ValidationException {
    public PasswordRequiredException(String message) {
        super(message);
    }
}
