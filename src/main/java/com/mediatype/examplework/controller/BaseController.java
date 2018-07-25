package com.mediatype.examplework.controller;

import com.mediatype.examplework.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class BaseController {

    private JpaRepository jpaRepository;

    @PostMapping(value = "/save")
    @Transactional
    public ResponseEntity saveImage(@RequestBody Image image){

        jpaRepository.save(image);

        return new ResponseEntity(image, HttpStatus.CREATED);
    }

    public void setJpaRepository(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
}
