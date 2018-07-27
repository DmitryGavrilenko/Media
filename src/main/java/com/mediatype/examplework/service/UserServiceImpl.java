package com.mediatype.examplework.service;

import com.mediatype.examplework.model.Image;
import com.mediatype.examplework.model.User;
import com.mediatype.examplework.dao.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
        super.setJpaRepository(userRepository);
    }
    @Override
    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public boolean saveUserForm(String name, String password, String email, Image image) {

        User user = convertToUser(name, password, email, image);

        saveEntity(user);

        return true;
    }

    @Override
    public User convertToUser(String name, String password, String email, Image image) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        if(image != null) user.getImages().add(image);

        return user;
    }

}
