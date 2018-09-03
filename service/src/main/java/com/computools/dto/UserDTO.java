package com.computools.dto;

import com.computools.path.Path;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;


public class UserDTO {

    private final String PATH = Path.PATH.getPath();

    private String name;

    @Size(min = 6, max = 12)
    private String password;

    private String email;

    private MultipartFile file;

    private String path;

    public UserDTO(String name, String email, String password
            , MultipartFile file, String path){
        this.name = name;
        this.email = email;
        this.password = password;
        this.file = file;
        this.path = path;
    }

    public UserDTO() {

    }

    public String getPath() {
        return file == null ? PATH : PATH  + file.getOriginalFilename();
    }


//    public String getPath() {
//        return path;
//    }

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
