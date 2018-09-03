package com.computools.web.dao;

import com.computools.audit.dao.ImageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ImageRepositoryTest {

    private final String TEST_PATH = "test_path";

    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void findByPath() {
//        Image image = new Image();
//        image.setPath(TEST_PATH);
//        entityManager.persist(image);
//        Image expectedImage = imageRepository.findByPath(TEST_PATH);
//
//        assertEquals(image, expectedImage);
    }
}
