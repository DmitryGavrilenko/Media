package com.computools.web.controller;

import com.computools.dto.UserDTO;
import com.computools.service.ImageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
public class ImageControllerTest {

    private ImageController imageController;

    @MockBean
    private ImageServiceImpl imageService;

    @Mock
    private UserDTO userDTO;

    @Before
    public void initController(){
        imageController = new ImageController(imageService);
    }

    @Test
    public void uploadImage() {
        imageController.uploadImage(userDTO);
        verify(imageService, times(1)).saveImage(userDTO);
    }

    @Test
    public void getImageByName() {
        imageController.getImageByName(any(String.class));
        verify(imageService, times(1)).getImageStreamResource(any());
        InputStreamResource image = mock(InputStreamResource.class);
        when(imageService.getImageStreamResource(any())).thenReturn(image);
    }

    @Test
    public void downloadImageByName() {
        imageController.downloadImageByName(any(String.class));
        verify(imageService, times(1)).getImageStreamResource(any());
        InputStreamResource image = mock(InputStreamResource.class);
        when(imageService.getImageStreamResource(any())).thenReturn(image);
    }
}