package com.computools.service;


import com.computools.audit.dao.UserRepository;
import com.computools.audit.model.Image;
import com.computools.audit.model.User;
import com.computools.dto.UserDTO;
import com.computools.path.Path;
import exception.NotFoundException;
import message.UserMessage;
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
        image.setPath(Path.PATH.getPath() + userDTO.getFile().getOriginalFilename());
        image.setUser(user);
        // TODO add additional logic to check image path imageService.pathAlreadyExists(Path.PATH.getPath() + userDTO.getFile().getOriginalFilename());
        imageService.saveEntity(image);
        imageService.saveImage(userDTO.getFile());
        return true;
    }

}
