package com.mediatype.examplework.dao;

import com.mediatype.examplework.model.Image;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface ImageRepository extends JpaRepository<Image, UUID> {

    Image findByPath(String path);

}
