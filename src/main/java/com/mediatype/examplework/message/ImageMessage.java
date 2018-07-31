package com.mediatype.examplework.message;

public enum ImageMessage {

    NOT_FOUND("Image not found"),
    FAILED_SAVE_FILE("failed to save the file"),
    SUCCESS_UPLOAD("File successfully uploaded"),
    SAVE_SUCCESS("Image successfully saved"),
    FILE_ALREADY_EXISTS("Image already exists");

    private String message;

    ImageMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
