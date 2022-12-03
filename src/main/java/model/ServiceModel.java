package model;

import entity.Service;
import dao.*;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

public class ServiceModel implements Model<Service> {
    private ServiceDAO serviceDao = new ServiceDAO();


    @Override
    public Service getById(int id) {
        List<Service> services = getAll();
        for(Service service : services){
            if(service.getId() == id){
                return service;
            }
        }
        return null;
    }

    public Service getByName(String name){
        List<Service> services = getAll();
        for(Service service : services){
            if(service.getName().equals(name)){
                return service;
            }
        }
        return null;
    }

    @Override
    public List<Service> getAll() {
        return serviceDao.read();
    }

    @Override
    public void updateById(int id, Service entity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void create(Service entity) {

    }
}
