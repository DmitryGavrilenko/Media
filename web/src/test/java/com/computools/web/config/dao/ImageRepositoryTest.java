package com.computools.web.config.dao;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.model.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest(excludeAutoConfiguration = FlywayAutoConfiguration.class)
public class ImageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void findByPath() {
        Image image = new Image();
        image.setPath("test");
        entityManager.persist(image);
        Image expectedImage = imageRepository.findByPath("test");
        assertEquals(image.getPath(), expectedImage.getPath());
    }
}
