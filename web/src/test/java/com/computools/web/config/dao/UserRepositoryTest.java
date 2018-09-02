package com.computools.web.config.dao;

import com.computools.audit.dao.UserRepository;
import com.computools.audit.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest(excludeAutoConfiguration = FlywayAutoConfiguration.class)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmail() {
        User user = new User();
        user.setEmail("tets@email.com");
        entityManager.persist(user);
        User expectedUser = userRepository.findByEmail("tets@email.com");
        assertNotNull(expectedUser);
        assertEquals(expectedUser.getEmail(), user.getEmail());
    }
}
