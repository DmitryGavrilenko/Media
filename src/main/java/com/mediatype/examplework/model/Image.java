package com.mediatype.examplework.model;

import javax.persistence.*;

@Entity
@Table(name = "img")
public class Image extends BaseModel{

    private String name;

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
