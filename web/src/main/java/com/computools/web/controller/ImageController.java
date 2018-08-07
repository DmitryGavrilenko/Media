
package com.computools.web.controller;
import com.computools.dto.UserDTO;
import com.computools.service.ImageServiceImpl;
import message.ImageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import response.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;


@Controller
@RequestMapping(value = "/image")
public class ImageController {


    private ImageServiceImpl imageService;

    @Autowired
    public ImageController(ImageServiceImpl imageService){
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<Response> uploadImage(UserDTO userDTO){

        imageService.saveImage(userDTO);

        return new ResponseEntity<>(new Response(ImageMessage.SUCCESS_UPLOAD.getMessage()
                , HttpStatus.CREATED, HttpStatus.CREATED.toString()), HttpStatus.CREATED);
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
