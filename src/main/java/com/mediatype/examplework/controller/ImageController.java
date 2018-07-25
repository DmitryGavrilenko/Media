package com.mediatype.examplework.controller;

import com.mediatype.examplework.model.Image;
import com.mediatype.examplework.repository.ImageRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
@RequestMapping(value = "/image")
public class ImageController extends BaseController{

    private ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
        super.setJpaRepository(imageRepository);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<Resource> get(String param) throws IOException {

        File file = new File("images/lake.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource resource = new InputStreamResource(fileInputStream);

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .body(resource);
    }

    @GetMapping(path = "/load", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity getImage() throws IOException {
        File file = new File("images/lake.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStream.read(bytes);
        return new ResponseEntity(bytes, HttpStatus.OK);
    }


    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(String param) throws IOException {

        File file = new File("images/lake.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource resource = new InputStreamResource(fileInputStream);

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(resource);
    }

    @PostMapping(value = "/upload", produces = "image/jpeg")
    public void uploadFile(@RequestParam("image") MultipartFile file,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setPath("images/");
        imageRepository.save(image);
        byte[] bytes = file.getBytes();
        FileOutputStream fileOutputStream = new FileOutputStream("images/" + file.getOriginalFilename());
        fileOutputStream.write(bytes);
    }


}
