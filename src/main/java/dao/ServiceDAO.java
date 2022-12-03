package dao;

import entity.Service;
import DB.Data;

import java.util.*;

public class ServiceDAO implements DAO<Service>{
    private Data data = new Data();

    @Override
    public void create(Service service) {data.addService(service);}
    @Override
    public List<Service> read() {return data.getServices();}

    @Override
    public void update(int id, Service service) {

    }

    @Override
    public void deleteById(int id) {

    }
}
