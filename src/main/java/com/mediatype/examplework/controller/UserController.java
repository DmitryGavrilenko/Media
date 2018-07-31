package com.mediatype.examplework.controller;

import com.mediatype.examplework.dto.UserDTO;
import com.mediatype.examplework.message.ImageMessage;
import com.mediatype.examplework.message.UserMessage;
import com.mediatype.examplework.model.User;
import com.mediatype.examplework.response.Response;
import com.mediatype.examplework.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserServiceImpl userService;
    private ModelMapper modelMapper;

    public UserController(UserServiceImpl userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/save/form")
    @Transactional
    public ResponseEntity<Response> saveUserForm(@Valid UserDTO userDTO){
        userService.saveUserForm(userDTO);
        return new ResponseEntity<>(new Response(UserMessage.CREATED.getMessage()
                , HttpStatus.CREATED, HttpStatus.CREATED.toString()),HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(value = "/save/by")
    public ResponseEntity<Response> saveUser(@RequestBody @Valid UserDTO userDTO){

        User user = modelMapper.map(userDTO, User.class);

        userService.saveEntity(user);

        return new ResponseEntity<>(new Response(UserMessage.CREATED.getMessage()
                , HttpStatus.CREATED, HttpStatus.CREATED.toString()),HttpStatus.CREATED);

    }

    @PostMapping(value = "/save/image")
    public ResponseEntity<Response> saveImageForUser(UserDTO userDTO){

        userService.saveImageForUser(userDTO);

        return new ResponseEntity<>(new Response(ImageMessage.SAVE_SUCCESS.getMessage()
                , HttpStatus.CREATED, HttpStatus.CREATED.toString()),HttpStatus.CREATED);
    }

}
