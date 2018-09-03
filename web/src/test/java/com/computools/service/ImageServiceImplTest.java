package com.computools.service;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.model.Image;
import com.computools.dto.UserDTO;
import com.computools.utils.MultipartFileImpl;
import com.computools.web.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public class ImageServiceImplTest {

    private final String FILE_NAME = "lake.jpg";

    private MultipartFile multipartFile;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Value("${image_path}")
    private String path;

    @PostConstruct
    public void deleteRows(){
        imageRepository.deleteAll();
        multipartFile = new MultipartFileImpl();
    }

    @Test
    public void saveImage() throws IOException {
        File deleteFile = new File(path + FILE_NAME);
        deleteFile.delete(); // Delete image from folder for testing saveImage method
        imageService.saveImage(multipartFile);
        File file = new File(path + multipartFile.getOriginalFilename());
        assertTrue(file.exists());
    }

    @Test
    public void saveImage1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPath(path);
        userDTO.setFile(multipartFile);
        imageService.saveImage(userDTO);
        Image image = imageRepository.findByPath(path + FILE_NAME);
        assertNotNull(image);
        assertEquals(image.getPath(), path + FILE_NAME);
    }

    @Test
    public void getImageStreamResource() throws IOException {
        InputStreamResource image = null;
        InputStream inputStream = new FileInputStream(path + FILE_NAME);
        image = new InputStreamResource(inputStream);
        InputStreamResource expectedImage = imageService.getImageStreamResource(FILE_NAME);
        assertNotNull(image);
        assertNotNull(expectedImage);
        assertEquals(image.getFilename(), expectedImage.getFilename());
    }
}