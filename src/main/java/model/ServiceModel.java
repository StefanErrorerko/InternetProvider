package model;

import entity.Service;
import dao.*;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.SQLException;
import java.util.List;

public class ServiceModel implements Model<Service> {
    private ServiceDAO serviceDao = new ServiceDAO();


    @Override
    public Service getById(int id) throws  SQLException{
        List<Service> services = getAll();
        for(Service service : services){
            if(service.getId() == id){
                return service;
            }
        }
        return null;
    }

    public Service getByName(String name) throws SQLException{
        List<Service> services = getAll();
        for(Service service : services){
            if(service.getName().equals(name)){
                return service;
            }
        }
        return null;
    }

    @Override
    public List<Service> getAll() throws SQLException {
        return serviceDao.read();
    }

    @Override
    public void updateById(int id, Service entity) throws  SQLException{

    }

    @Override
    public void deleteById(int id) throws SQLException{

    }

    @Override
    public void create(Service entity) throws SQLException{

    }
}