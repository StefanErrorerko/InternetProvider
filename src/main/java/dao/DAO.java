package dao;

import entity.Service;

import java.sql.SQLException;
import java.util.*;

public interface DAO<T> {
    void create (T entity) throws SQLException;
    List<T> read() throws SQLException;
    void update(int id, T entity) throws SQLException;
    void deleteById(int id) throws SQLException;
}