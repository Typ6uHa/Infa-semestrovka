package database.dao;

import java.util.List;

public interface Dao<T> {
    void insert(T object);
    T getById(int id);
    void update(T object);
    void delete(T object);
    List<T> getAll();
}
