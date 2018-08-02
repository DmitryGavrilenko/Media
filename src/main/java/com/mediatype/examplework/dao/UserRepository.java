package com.mediatype.examplework.dao;

import com.mediatype.examplework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

}
