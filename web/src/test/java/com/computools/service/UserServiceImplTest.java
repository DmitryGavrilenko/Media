package com.computools.service;

import com.computools.audit.dao.UserRepository;
import com.computools.audit.model.Image;
import com.computools.audit.model.User;
import com.computools.dto.UserDTO;
import com.computools.utils.MultipartFileImpl;
import com.computools.web.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Profile(value = "test")
public class UserServiceImplTest {

    private MultipartFile multipartFile;

    private UserDTO userDTO;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${image_path}")
    private String path;

    @PostConstruct
    public void init(){
        userRepository.deleteAll();
        multipartFile = new MultipartFileImpl();
        userDTO = new UserDTO("Dmitry", "gavrilenko6f@gmail.com"
                            , "12345678", multipartFile, path);
        Image image = new Image();
        image.setPath(userDTO.getPath());
        User user = modelMapper.map(userDTO, User.class);
        user.setImages(Arrays.asList(image));
        userService.saveEntity(user);
    }


    @Test
    public void findUserByEmail() {
        User expectedUser = userService.findUserByEmail("gavrilenko6f@gmail.com");
        assertNotNull(expectedUser);
    }

    @Test
    public void saveUserForm() {
        UserDTO userDTO = new UserDTO("Dmitry", "dmitry@gmail.com"
                , "12344556", multipartFile, path);
        userService.saveUserForm(userDTO);
        User expectedUser = userService.findUserByEmail("dmitry@gmail.com");
        User testUser = userRepository.findByEmail("dmitry@gmail.com");
        assertNotNull(expectedUser);
        assertEquals(expectedUser.getId(), testUser.getId());

    }

    @Test
    public void saveImageForUser() {
        assertTrue(userService.saveImageForUser(userDTO));
        User expectedUser = userService.findUserByEmail(userDTO.getEmail());
        assertNotNull(expectedUser);
        assertEquals(expectedUser.getName(), userDTO.getName());
        assertNotNull(expectedUser.getId());
    }

}