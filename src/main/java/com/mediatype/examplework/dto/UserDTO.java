package com.mediatype.examplework.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

@JsonSerialize
public class UserDTO {

    private String name;

    @Size(min = 6, max = 12)
    private String password;

    private String email;

    private MultipartFile file;

    private String path;

    public UserDTO() {

    }

    public String getPath() {
        return "images/" + file.getOriginalFilename();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
