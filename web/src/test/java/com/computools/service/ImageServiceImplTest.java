package com.computools.service;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.model.Image;
import com.computools.dto.UserDTO;
import com.computools.path.Path;
import com.computools.web.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class ImageServiceImplTest {

    private final String FILE_NAME = "lake.jpg";

    @Autowired
    private ImageService imageService;

    @Autowired
    private MultipartFile multipartFile;

    @Autowired
    private ImageRepository imageRepository;

    @Before
    public void deleteRow(){
        Image image = imageRepository.findByPath(Path.PATH.getPath());
        if (image != null) imageRepository.delete(image);
    }

    @Test
    public void saveImage() throws IOException {
        File deleteFile = new File(Path.PATH.getPath() + FILE_NAME);
        deleteFile.delete(); // Delete image from folder for testing saveImage method
        imageService.saveImage(multipartFile);
        File file = new File(Path.PATH.getPath() + multipartFile.getOriginalFilename());
        assertTrue(file.exists());
    }

    @Test
    public void saveImage1() { //TODO image saved twice
        UserDTO userDTO = new UserDTO();
        userDTO.setPath(Path.PATH.getPath());
        userDTO.setFile(multipartFile);
        imageService.saveImage(userDTO);
        Image image = imageRepository.findByPath(Path.PATH.getPath() + FILE_NAME);
        assertNotNull(image);
        assertEquals(image.getPath(), Path.PATH.getPath() + FILE_NAME);
    }

    @Test
    public void getImageStreamResource() throws IOException {
        InputStreamResource image = null;
        InputStream inputStream = new FileInputStream(Path.PATH.getPath() + FILE_NAME);
        image = new InputStreamResource(inputStream);
        InputStreamResource expectedImage = imageService.getImageStreamResource(FILE_NAME);
        assertNotNull(image);
        assertNotNull(expectedImage);
        assertEquals(image.getFilename(), expectedImage.getFilename());
    }
}