package fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions;

public class TimeEntryConstraintException extends RuntimeException{
    public TimeEntryConstraintException(String message) {
        super(message);
    }

    public TimeEntryConstraintException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeEntryConstraintException(Throwable cause) {
        super(cause);
    }

    public TimeEntryConstraintException() {
    }
}
