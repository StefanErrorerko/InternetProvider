package model;

import dao.*;
import entity.*;
import view.SubscribeDocView;

import java.sql.SQLException;
import java.util.List;

public class SubscribeDocModel implements Model<SubscribeDoc> {
    private SubscribeDocDAO docDAO = new SubscribeDocDAO();


    @Override
    public SubscribeDoc getById(int id) throws SQLException {
        return  null;
    }

    @Override
    public List<SubscribeDoc> getAll() throws SQLException{
        return docDAO.read();
    }

    @Override
    public void updateById(int id, SubscribeDoc entity) throws SQLException{

    }

    @Override
    public void deleteById(int id) throws SQLException{

    }

    @Override
    public void create(SubscribeDoc entity) throws SQLException{
        docDAO.create(entity);
    }
    public void create(Tariff tariff, Service service, Customer customer) throws SQLException{
        SubscribeDoc doc = new SubscribeDoc(service.getId(), tariff.getId(), customer.getId());
        create(doc);
    }


}