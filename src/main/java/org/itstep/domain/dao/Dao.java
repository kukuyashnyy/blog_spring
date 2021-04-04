package org.itstep.domain.dao;

import java.util.List;

public interface Dao <T, ID> {
    List<T> findAll();
    T findById(ID id);
    ID save(T data);
    T update(T data);
    void delete(ID id);
}
