package shared.exceptions;

public class ScheduleConflictException extends ValidationException {
    public ScheduleConflictException(String message) {
        super(message);
    }
}
