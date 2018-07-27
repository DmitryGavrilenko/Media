package com.mediatype.examplework.service;

import com.mediatype.examplework.model.Image;
import com.mediatype.examplework.model.User;

public interface UserService{

    User findUserByEmail(String email);

    boolean saveUserForm(String name, String password, String email, Image image);

    User convertToUser(String name, String password, String email, Image image);

}
