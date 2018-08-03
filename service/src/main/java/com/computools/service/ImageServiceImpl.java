package com.computools.service;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.model.Image;
import com.computools.dto.UserDTO;
import exception.FileExistsException;
import exception.NotFoundException;
import exception.SaveFileException;
import message.ImageMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl extends BaseServiceImpl<Image> implements ImageService{

    private ModelMapper modelMapper;

    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
        super.setJpaRepository(imageRepository);
    }

    public void saveImage(MultipartFile file) {

        try {
            Files.copy(file.getInputStream(), Paths.get("images/" + file.getOriginalFilename()));
        }catch(FileAlreadyExistsException e){
            throw new FileExistsException(ImageMessage.FILE_ALREADY_EXISTS.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString());
        }catch (IOException e) {
            imageRepository.delete(imageRepository.findByPath("images/" + file));
            throw new SaveFileException(ImageMessage.FAILED_SAVE_FILE.getMessage()
                    , HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }

    }

    @Override
    public void saveImage(UserDTO userDTO) {

        Image image = modelMapper.map(userDTO, Image.class);
        imageRepository.save(image);
        saveImage(userDTO.getFile());

    }

    @Override
    public InputStreamResource getImageStreamResource(String name) {

        InputStreamResource image = null;
        File file = new File("images/" + name);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            image = new InputStreamResource(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new NotFoundException(ImageMessage.NOT_FOUND.getMessage()
                    , HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString());
        }

        return image;
    }


}
