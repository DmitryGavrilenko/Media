
package com.mediatype.examplework.controller;
import com.mediatype.examplework.dto.UserDTO;
import com.mediatype.examplework.message.ImageMessage;
import com.mediatype.examplework.response.Response;
import com.mediatype.examplework.service.ImageServiceImpl;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/image")
public class ImageController {


    private ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService){
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload")
    @Transactional
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
