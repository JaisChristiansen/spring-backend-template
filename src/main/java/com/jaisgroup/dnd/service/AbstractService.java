package com.jaisgroup.dnd.service;

import com.jaisgroup.dnd.model.AbstractEntity;

import java.util.Optional;

public interface AbstractService<T extends AbstractEntity, ID> {

    Optional<T> getById(ID id);
    T persist(T entity);
    void delete(ID id);
}
