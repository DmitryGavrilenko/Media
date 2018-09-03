package com.computools.audit.dao;

import com.computools.audit.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {

    Image findByPath(String path);

}
