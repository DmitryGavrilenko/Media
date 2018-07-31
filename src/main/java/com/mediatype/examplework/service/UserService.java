package com.mediatype.examplework.service;

import com.mediatype.examplework.dto.UserDTO;
import com.mediatype.examplework.model.User;

public interface UserService{

    User findUserByEmail(String email);

    boolean saveUserForm(UserDTO userDTO);

    boolean saveImageForUser(UserDTO userDTO);

}
