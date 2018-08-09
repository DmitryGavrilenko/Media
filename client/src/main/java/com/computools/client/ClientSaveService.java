package com.computools.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import response.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ClientSaveService {

    private RestTemplate restTemplate;
    private final Resource file;


    public ClientSaveService(@Value("${server_url}") String url, RestTemplate restTemplate,
                             Resource resource) throws IOException {
        this.restTemplate = restTemplate;
        this.file = resource;
    }


    @Async
    public Future<String> save(int batchSize){


        for(int i = 0; i < 8000000; i++){

                LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
                map.add("file", null);
                map.add("name", "testname");
                map.add("email", "testemail");
                map.add("password","12345678");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);

                HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(
                        map, headers);
                ResponseEntity<Response> infoResponce = restTemplate.postForEntity("http://localhost:9000/user/save/form",
                        requestEntity, Response.class);


        }

        return CompletableFuture.completedFuture("Done");
    }

}
