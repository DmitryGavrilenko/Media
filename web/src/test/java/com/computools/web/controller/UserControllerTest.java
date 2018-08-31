package com.computools.web.controller;

import com.computools.audit.model.User;
import com.computools.dto.UserDTO;
import com.computools.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class UserControllerTest {

    private UserController userController;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private ModelMapper modelMapper;

    @Before
    public void init(){
        userController = new UserController(userService, modelMapper);
    }

    @Test
    public void saveUserForm() {
        UserDTO userDTO = new UserDTO();
        userController.saveUserForm(userDTO);
        verify(userService, times(1)).saveUserForm(userDTO);
    }

    @Test
    public void saveUser() {
        UserDTO userDTO = new UserDTO();
        userController.saveUser(userDTO);
        verify(modelMapper, times(1)).map(userDTO, User.class);
        verify(userService, times(1)).saveEntity(any());
    }

    @Test
    public void saveImageForUser() {
        UserDTO userDTO = new UserDTO();
        userController.saveImageForUser(userDTO);
        verify(userService, times(1)).saveImageForUser(userDTO);
    }
}