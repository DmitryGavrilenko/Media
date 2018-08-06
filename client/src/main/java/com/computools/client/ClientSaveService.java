package com.computools.client;

import com.computools.client.file.MultipartImpl;
import com.computools.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import response.Response;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class ClientSaveService {

    private RestTemplate restTemplate;

    private String url;

    private MultipartImpl multipart;

    public ClientSaveService(@Value("${server_url}") String url, RestTemplate restTemplate, MultipartImpl multipart) {
        this.url = url;
        this.restTemplate = restTemplate;
        this.multipart = multipart;
    }

    @Async
    public Future<String> save(int batchSize){

        List<Image>images = new ArrayList<>();
        for(int i = 0; i < batchSize; i++){

            try {
                UserDTO userDTO = new UserDTO();
                userDTO.setEmail("testemail");
                userDTO.setPassword("12345678");
                userDTO.setName("testname");
                userDTO.setFile(multipart);
////                userDTO.setFile(file);
//                TestDTO testDTO = new TestDTO();
//                testDTO.setEmail("testemail");
//                testDTO.setPassword("12345678");
//                testDTO.setName("testname");

                ResponseEntity<Response> infoResponce = restTemplate.postForEntity("http://localhost:8009/user/save/form",
                        userDTO, Response.class);
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }
        return CompletableFuture.completedFuture("Done");
    }

}
