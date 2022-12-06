package model;

import java.sql.SQLException;
import java.util.List;

public interface Model<T> {
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    void updateById(int id, T entity) throws SQLException;
    void deleteById(int id) throws SQLException;
    void create(T entity) throws SQLException;
}