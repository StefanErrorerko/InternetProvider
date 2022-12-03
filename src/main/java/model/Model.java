package model;

import java.util.List;

public interface Model<T> {
    T getById(int id);
    List<T> getAll();
    void updateById(int id, T entity);
    void deleteById(int id);
    void create(T entity);
}
