package com.mediatype.examplework.controller;

import com.mediatype.examplework.model.Image;
import com.mediatype.examplework.model.User;
import com.mediatype.examplework.service.ImageServiceImpl;
import com.mediatype.examplework.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/*TODO PLEASE CHANGE ALL RESPONSES TO OBJECTS (NOT STRINGS ), MODEL MAPPER
* FIXME PLEASE ADD CONTROLLER ADVICE  FOR INTERCEPT EXCEPTIONS AND PROVIDE RESPONSES WITHOUT STACK TRACES */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserServiceImpl userService;
    private ImageServiceImpl imageService;

    public UserController(UserServiceImpl userService, ImageServiceImpl imageService){
        this.userService = userService;
        this.imageService = imageService;
    }

    @PostMapping(value = "/save/form")
    @Transactional
    public ResponseEntity<String> saveUserForm(@RequestParam(value = "file",required = false) MultipartFile file, // TODO CHANGE AS FORM - DATA (DTO OBJECT FOR VALIDATION)
                                       @RequestParam("name") String name,
                                       @RequestParam("password") String password,
                                       @RequestParam("email") String email){

        if(file == null){ // FIXME  ALL SUCH VALIDATION MUST BE DONE INSIDE SERVICE LAYER
            User user =  userService.convertToUser(name, password, email, null);
            saveUser(user);
            return new ResponseEntity<>("User with name " + name + ", successfully created",HttpStatus.CREATED);
        }

        Image image = imageService.convertMultipartFileToImage(file);
        if(userService.saveUserForm(name, password, email, image)) imageService.uploadImageToFolder(file);
        else return new ResponseEntity<>("Cannot created user with name " + name, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>("User with name " + name + ", successfully created",HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/save/by")
    public ResponseEntity<String> saveUser(@RequestBody User user){

        userService.saveEntity(user);

        return new ResponseEntity<>("User with name " + user.getName() + ", successfully created",HttpStatus.CREATED);

    }


}
