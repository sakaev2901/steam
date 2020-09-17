package ru.itis.reposirtories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    T findById(ID id);
    T save(T model);
    T update(T model);
    T delete(T model);
    List<T> findAll();
}
