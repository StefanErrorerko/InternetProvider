package model;

import dao.CustomerDAO;
import entity.Customer;

import java.util.List;

public class CustomerModel implements Model<Customer> {
    private CustomerDAO customerDAO = new CustomerDAO();


    @Override
    public Customer getById(int id) {
        return null;
    }

    public Customer getByLogin(String login){
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

    public Customer getByParol(String parol){
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
    public List<Customer> getAll() {
        return customerDAO.read();
    }

    public double getBalance(int id){
        List<Customer> customers = getAll();
        for(Customer customer : customers){
            if(customer.getId() == id){
                return customer.getSum();
            }
        }
        return -1000000;
    }

    @Override
    public void updateById(int id, Customer entity) {

    }

    @Override
    public void deleteById(int id) {

    }

    public void updateById(int id, double sum){
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

    public void updateById(int id, boolean isBanned){
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
    public void create(Customer entity) {
        customerDAO.create(entity);
        entity.setId(getAll().get(getAll().size() - 1).getId());
    }
}
