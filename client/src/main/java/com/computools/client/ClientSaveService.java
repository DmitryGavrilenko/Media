package com.computools.client;

import com.computools.client.file.MultipartImpl;
import com.computools.dto.UserDTO;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
//import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
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

        for(int i = 0; i < batchSize; i++){

            try(CloseableHttpClient httpclient = HttpClients.createDefault()
                    ) {

                File file = new File("images/lake.jpg");
                String message ="testname";

                HttpEntity data = MultipartEntityBuilder.create()
                        .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                        .addBinaryBody("file", file, ContentType.IMAGE_JPEG, file.getName())
                        .addTextBody("name", "testname")
                        .addTextBody("email", "testemail")
                        .addTextBody("password", "12345678")
                        .build();

                HttpUriRequest request = RequestBuilder
                        .post("http://localhost:9000/user/save/form")
                        .setEntity(data)
                        .build();

//
                CloseableHttpResponse httpResponce = httpclient.execute(request);
//                System.out.println(httpResponce.getEntity());


                // request with restTemplate //

//                LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
//                FileSystemResource value = new FileSystemResource(new File("images/lake.jpg"));
////                map.add("file", new ClassPathResource("images/lake.jpg"));
//                map.add("file", value);
//                map.add("name", "testname");
//                map.add("email", "testemail");
//                map.add("password","12345678");
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//                HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(
//                        map, headers);
//                ResponseEntity<Response> infoResponce = restTemplate.postForEntity("http://localhost:9000/user/save/form",
//                        requestEntity, Response.class);


//
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }
        return CompletableFuture.completedFuture("Done");
    }

}
