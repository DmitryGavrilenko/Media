package com.mediatype.examplework.dao;

import com.mediatype.examplework.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findByPath(String path);

}
