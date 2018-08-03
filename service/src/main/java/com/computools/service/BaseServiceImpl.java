package com.computools.service;

import org.springframework.data.jpa.repository.JpaRepository;

public class BaseServiceImpl<E> {

    private JpaRepository jpaRepository;

    public void setJpaRepository(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public boolean saveEntity(E entity) {

        jpaRepository.save(entity);

        return true;
    }
}
