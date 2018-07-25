package com.mediatype.examplework.repository;

import com.mediatype.examplework.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findByName(String name);

}
