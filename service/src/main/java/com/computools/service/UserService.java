package com.computools.service;


import com.computools.audit.model.User;
import com.computools.dto.UserDTO;

public interface UserService{

    User findUserByEmail(String email);

    boolean saveUserForm(UserDTO userDTO);

    boolean saveImageForUser(UserDTO userDTO);

}
