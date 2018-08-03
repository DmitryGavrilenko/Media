package com.computools.service;

import com.computools.dto.UserDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImage(MultipartFile file);

    void saveImage(UserDTO userDTODTO);

    InputStreamResource getImageStreamResource(String name);


}
