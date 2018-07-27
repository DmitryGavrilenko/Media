package com.mediatype.examplework.service;

import org.springframework.data.jpa.repository.JpaRepository;

public class BaseServiceImpl<E> implements BaseService<E> {

    private JpaRepository jpaRepository;

    public void setJpaRepository(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean saveEntity(E entity) {

        jpaRepository.save(entity);

        return true;
    }
}
