package message;

public enum UserMessage {
    NOT_FOUND("User not found"),
    CREATED("User successfully created");

    private String message;

    UserMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
