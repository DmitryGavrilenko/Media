package com.mediatype.examplework.service;

import com.mediatype.examplework.model.Image;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image convertMultipartFileToImage(MultipartFile file);

    void uploadImageToFolder(MultipartFile file);

    InputStreamResource getImageStreamResource(String name);

    boolean saveImageForUser(String email, MultipartFile file);

}
