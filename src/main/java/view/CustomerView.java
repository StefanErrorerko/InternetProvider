package view;

import entity.Customer;
import entity.Service;

import java.util.List;

public class CustomerView implements View<Customer> {
    @Override
    public void show(List<Customer> entities) {
        System.out.println("Customers");
        for(Customer customer : entities){
            System.out.print("Id: ");
            System.out.print(customer.getId());
            System.out.print(", Name: ");
            System.out.println(customer.getName());
            System.out.print("Surname: ");
            System.out.println(customer.getSurname());
            System.out.print("Balance: ");
            System.out.println(customer.getSum());
            System.out.print("Login: ");
            System.out.print(customer.getLogin());
            System.out.print(", Parol: ");
            System.out.println(customer.getParol());
            System.out.print("isBanned: ");
            System.out.println(customer.banStatus());
        }
    }

    @Override
    public void showCreated(Customer entity) {

    }

    @Override
    public void showDeleted() {

    }

    @Override
    public void showUpdated() {

    }

    public void showBanned(){
        System.out.println("Customer was banned");
    }

    public void showUnbanned(){
        System.out.println("Customer was unbanned");
    }
}
