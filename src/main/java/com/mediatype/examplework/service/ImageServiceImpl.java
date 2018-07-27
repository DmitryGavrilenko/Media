package com.mediatype.examplework.service;

import com.mediatype.examplework.dao.ImageRepository;
import com.mediatype.examplework.model.Image;
import com.mediatype.examplework.model.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class ImageServiceImpl extends BaseServiceImpl<Image> implements ImageService{

    private ImageRepository imageRepository;

    private UserServiceImpl userService;

    public ImageServiceImpl(ImageRepository imageRepository, UserServiceImpl userService){
        this.imageRepository = imageRepository;
        this.userService = userService;
        super.setJpaRepository(imageRepository);
    }

    @Override
    public Image convertMultipartFileToImage(MultipartFile file) {
        Image image = new Image();
        image.setPath("images/" + file.getOriginalFilename());

        return image;
    }

    @Override
    public void uploadImageToFolder(MultipartFile file) {

        try {

            byte[] buffer = file.getBytes();
            FileOutputStream uploadFile = new FileOutputStream("images/" + file.getOriginalFilename());
            uploadFile.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public InputStreamResource getImageStreamResource(String name) {

        InputStreamResource image = null;
        File file = new File("images/" + name);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            image = new InputStreamResource(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return image;
    }

    @Override
    public boolean saveImageForUser(String email, MultipartFile file) {

        User user = userService.findUserByEmail(email);

        Image image = convertMultipartFileToImage(file);

        user.getImages().add(image);

        saveEntity(image);

        uploadImageToFolder(file);

        return true;
    }

}
