package com.computools.audit.model;

import javax.persistence.*;

@Entity
@Table(name = "img")
public class Image extends BaseModel{

    private String path;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    private User user;

    public Image() {
    }
    public Image(String path) {
        this.path = path;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

}
