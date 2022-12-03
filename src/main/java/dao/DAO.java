package dao;

import entity.Service;
import java.util.*;

public interface DAO<T> {
    void create(T entity);
    List<T> read();
    void update(int id, T entity);
    void deleteById(int id);
}
