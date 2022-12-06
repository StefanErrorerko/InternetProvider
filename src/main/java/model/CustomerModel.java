package model;

import dao.CustomerDAO;
import entity.Customer;
import entity.Tariff;

import java.sql.SQLException;
import java.util.List;

public class CustomerModel implements Model<Customer> {
    private CustomerDAO customerDAO = new CustomerDAO();


    @Override
    public Customer getById(int id) throws SQLException {
        List<Customer> customers = getAll();
        for(Customer customer : customers){
            if(customer.getId() == id){
                return customer;
            }
        }
        return null;
    }

    public Customer getByLogin(String login) throws  SQLException{
        List<Customer> customers = getAll();
        Customer customerByLogin = null;
        for(Customer customer : customers){
            if(customer.getLogin().equals(login)){
                customerByLogin = customer;
                return customerByLogin;
            }
        }
        return customerByLogin;
    }

    public Customer getByParol(String parol) throws SQLException{
        List<Customer> customers = getAll();
        Customer customerByParol = null;
        for(Customer customer : customers){
            if(customer.getParol().equals(parol)){
                customerByParol = customer;
                return customerByParol;
            }
        }
        return customerByParol;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return customerDAO.read();
    }

    public double getBalance(int id) throws SQLException {
        List<Customer> customers = getAll();
        for(Customer customer : customers){
            if(customer.getId() == id){
                return customer.getSum();
            }
        }
        return -1000000;
    }

    @Override
    public void updateById(int id, Customer entity) throws  SQLException{

    }

    @Override
    public void deleteById(int id) throws  SQLException{

    }

    public void updateById(int id, double sum) throws SQLException{
        Customer updatedCustomer;
        List<Customer> customers = getAll();
        for(Customer customer : customers){
            if(customer.getId() == id){
                updatedCustomer = customer;
                updatedCustomer.setSum(customer.getSum() + sum);
                customerDAO.update(id, updatedCustomer);
            }
        }
    }

    public void updateById(int id, boolean isBanned) throws SQLException{
        Customer updatedCustomer;
        List<Customer> customers = getAll();
        for(Customer customer : customers){
            if(customer.getId() == id){
                updatedCustomer = customer;
                updatedCustomer.setBan(isBanned);
                customerDAO.update(id, updatedCustomer);
            }
        }
    }

    @Override
    public void create(Customer entity) throws  SQLException{
        customerDAO.create(entity);
        entity.setId(getAll().get(getAll().size() - 1).getId());
    }
}