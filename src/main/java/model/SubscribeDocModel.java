package model;

import dao.*;
import entity.*;
import view.SubscribeDocView;

import java.util.List;

public class SubscribeDocModel implements Model<SubscribeDoc> {
    private SubscribeDocDAO docDAO = new SubscribeDocDAO();


    @Override
    public SubscribeDoc getById(int id) {
        return  null;
    }

    @Override
    public List<SubscribeDoc> getAll() {
        return docDAO.read();
    }

    @Override
    public void updateById(int id, SubscribeDoc entity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void create(SubscribeDoc entity) {
        docDAO.create(entity);
    }
    public void create(Tariff tariff, Service service, Customer customer){
        SubscribeDoc doc = new SubscribeDoc(service.getId(), tariff.getId(), customer.getId());
        create(doc);
    }


}
