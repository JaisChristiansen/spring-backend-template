package com.example.demo.service;

import com.example.demo.model.AbstractEntity;

import java.util.Optional;

public interface AbstractService<T extends AbstractEntity, ID> {

    Optional<T> getById(ID id);
    T persist(T entity);
    void delete(ID id);
}
