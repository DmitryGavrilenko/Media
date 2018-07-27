
package com.mediatype.examplework.controller;
import com.mediatype.examplework.service.ImageServiceImpl;
import com.mediatype.examplework.service.UserServiceImpl;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(value = "/image")
public class ImageController {


    private ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService, UserServiceImpl userService){
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload")
    @Transactional
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file){

        if(!imageService.saveEntity(imageService.convertMultipartFileToImage(file)))
            return new ResponseEntity<>("Cannot upload file", HttpStatus.INTERNAL_SERVER_ERROR);

        imageService.uploadImageToFolder(file);

        return new ResponseEntity<>("File successfully uploaded", HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload/for_user")
    public ResponseEntity<String> uploadImageForUser(@RequestParam("file") MultipartFile file, //TODO You must to process form-data
                                                     @RequestParam("email") String email){

        imageService.saveImageForUser(email, file);

        return new ResponseEntity<>("File successfully uploaded", HttpStatus.CREATED);
    }

    @GetMapping(value = "/get/{name}")
    public ResponseEntity<InputStreamResource> getImageByName(@PathVariable String name){

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageService.getImageStreamResource(name));

    }

    @GetMapping(value = "/download/{name}")
    public ResponseEntity<InputStreamResource> downloadImageByName(@PathVariable String name){

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(imageService.getImageStreamResource(name));
    }

}
