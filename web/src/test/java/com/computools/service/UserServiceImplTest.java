package com.computools.service;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.dao.UserRepository;
import com.computools.audit.model.Image;
import com.computools.audit.model.User;
import com.computools.dto.UserDTO;
import com.computools.path.Path;
import com.computools.web.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import utils.MultipartFileImpl;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private MultipartFile multipartFile;

    @PostConstruct
    public void init(){

        userRepository.deleteAll();

        Image image = new Image();
        image.setPath("images/");

        User user = new User();
        user.setEmail("gavrilenko6f@gmail.com");
        user.setName("Dmitry");
        user.setPassword("12345678");
        user.setImages(Arrays.asList(image));

        userRepository.save(user);
    }

    @Test
    public void findUserByEmail() {
        User expectedUser = userService.findUserByEmail("gavrilenko6f@gmail.com");
        assertNotNull(expectedUser);
    }

    @Test
    public void saveUserForm() {

        UserDTO userDTO = new UserDTO();
        userDTO.setPath("images/lake.jpg");
        userDTO.setPassword("123456789");
        userDTO.setName("Dmitry");
        userDTO.setEmail("dmitry@gmail.com");
        userDTO.setFile(multipartFile);

        userService.saveUserForm(userDTO);
        User expectedUser = userService.findUserByEmail("dmitry@gmail.com");
        User testUser = userRepository.findByEmail("dmitry@gmail.com");
        assertNotNull(expectedUser);
        assertEquals(expectedUser.getId(), testUser.getId());

    }

    @Test
    public void saveImageForUser() { // TODO rewrite this test
        MultipartFileImpl multipartData = new MultipartFileImpl();

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("gavrilenko6f@gmail.com");
        userDTO.setFile(multipartData);

        User user = userRepository.findByEmail("gavrilenko6f@gmail.com");
        assertNotNull(user);

        Image image = new Image();
        image.setPath(Path.PATH.getPath() + multipartData.getOriginalFilename());
        image.setUser(user);
        imageRepository.save(image);

        User testUser = userRepository.findByEmail("gavrilenko6f@gmail.com");
        assertNotNull(testUser);

        userService.saveImageForUser(userDTO);

        User expectedUser = userRepository.findByEmail("gavrilenko6f@gmail.com");

        assertEquals(expectedUser.getId(), testUser.getId());

    }

}