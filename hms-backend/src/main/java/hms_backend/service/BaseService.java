package hms_backend.service;

import hms_backend.model.BaseModel;

import java.util.List;

public interface BaseService<T extends BaseModel,K> {
    T create(T item);

    void update(T item);

    T getById(K id);

    void deleteById(K id);

    void delete(T item);

    List<T> findAll();

    Long count();
}
