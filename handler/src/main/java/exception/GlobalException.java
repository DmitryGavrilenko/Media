package exception;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {

    protected String message;
    protected HttpStatus status;
    protected String code;

    public GlobalException(String message, HttpStatus status, String code){
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
