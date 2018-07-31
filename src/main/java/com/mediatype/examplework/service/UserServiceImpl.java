package com.mediatype.examplework.service;

import com.mediatype.examplework.dto.UserDTO;
import com.mediatype.examplework.exception.NotFoundException;
import com.mediatype.examplework.message.UserMessage;
import com.mediatype.examplework.model.Image;
import com.mediatype.examplework.model.User;
import com.mediatype.examplework.dao.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private ImageServiceImpl imageService;

    public UserServiceImpl(UserRepository userRepository, ImageServiceImpl imageService, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        super.setJpaRepository(userRepository);
    }
    @Override
    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public boolean saveUserForm(UserDTO userDTO) {
        Image image = modelMapper.map(userDTO, Image.class);
        User user = image.getUser();
        user.getImages().add(image);
        saveEntity(user);
        imageService.saveImage(userDTO.getFile());

        return true;
    }

    @Override
    public boolean saveImageForUser(UserDTO userDTO) {
        User user = findUserByEmail(userDTO.getEmail());
        if(user == null) throw new NotFoundException(UserMessage.NOT_FOUND.getMessage()
                , HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString());
        Image image = new Image();
        image.setPath("images/" + userDTO.getFile().getOriginalFilename());
        image.setUser(user);
        imageService.saveEntity(image);
        imageService.saveImage(userDTO.getFile());
        return true;
    }

}
