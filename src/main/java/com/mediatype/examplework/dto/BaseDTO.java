package com.mediatype.examplework.dto;

import java.util.UUID;

public class BaseDTO {

    private UUID id;

    public BaseDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
