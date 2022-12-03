package dao;

import entity.Customer;
import DB.Data;

import java.util.List;

public class CustomerDAO implements DAO<Customer>{
    private Data data = new Data();

    @Override
    public void create(Customer customer) {
        data.addCustomer(customer);
    }
    @Override
    public List<Customer> read() {
        return data.getCustomers();
    }
    @Override
    public void update(int id, Customer customer) {
        data.setCustomerById(id, customer);
    }
    @Override
    public void deleteById(int id) {

    }
}
