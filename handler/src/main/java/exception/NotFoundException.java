package exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GlobalException {
    public NotFoundException(String message, HttpStatus status, String code) {
        super(message, status, code);
    }
}
