package com.mediatype.examplework.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "n_user")
public class User extends BaseModel{
    
    private String password;
    
    private String name;
    
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
