package fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
