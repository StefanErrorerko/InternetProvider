package controller;

import model.*;
import view.*;
import entity.*;

import java.util.*;

public class Controller {
    private CustomerView customerView;
    private ServiceView serviceView;
    private TariffView tariffView;
    private SubscribeDocView docView;

    private CustomerModel customerModel;
    private ServiceModel serviceModel;
    private TariffModel tariffModel;
    private SubscribeDocModel docModel;

    public void setAllModels(CustomerModel cM, ServiceModel sM, TariffModel tM, SubscribeDocModel dM){
        customerModel = cM;
        serviceModel = sM;
        tariffModel = tM;
        docModel = dM;
    }

    public void setAllViews(CustomerView cV, ServiceView sV, TariffView tV, SubscribeDocView dV){
        customerView = cV;
        serviceView = sV;
        tariffView = tV;
        docView = dV;
    }


    public List<Tariff> returnAllTariffs(){
        return tariffModel.getAll();
    }

    public List<Customer> returnAllCustomers(){
        return customerModel.getAll();
    }

    public Service returnServiceByName(String name){
        return serviceModel.getByName(name);
    }

    public Tariff returnTariffByName(String name){
        return tariffModel.getByName(name);
    }

    public Tariff returnTariffById(int id){
        return tariffModel.getById(id);
    }

    public Customer returnCustomerByLogin(String login){
        return customerModel.getByLogin(login);
    }

    public Customer returnCustomerByParol(String parol){
        return customerModel.getByParol(parol);
    }

    public void getAllServices(){
        List<Service> services = serviceModel.getAll();
        serviceView.show(services);
    }
    public void getAllCustomers(){
        List<Customer> customers = customerModel.getAll();
        customerView.show(customers);
    }
    public void getAllTariffs(){
        List<Tariff> tariffs = tariffModel.getAll();
        tariffView.show(tariffs);
    }
    public void createNewCustomer(Customer customer){
        customerModel.create(customer);
        customerView.showCreated(customer);
    }
    public void getTariffsAlphaSorted(){
        List<Tariff> tariffs = tariffModel.readTariffsAlphaSorted();
        tariffView.show(tariffs);
    }
    public void getTariffsAlphaRevSorted(){
        List<Tariff> tariffs = tariffModel.readTariffsAlphaRevSorted();
        tariffView.show(tariffs);
    }

    public void getTariffsPriceSorted(){
        List<Tariff> tariffs = tariffModel.readTariffsPriceSorted();
        tariffView.show(tariffs);
    }

    public void createNewDoc(Tariff tariff, Customer customer){
        customerModel.updateById(customer.getId(), -tariff.getPrice());
        Service service = serviceModel.getById(tariff.getServiceId());
        docModel.create(tariff, service, customer);
        if(customerModel.getBalance(customer.getId()) < 0){
            customer.ban();
        }
    }
    public void createNewTariff(Tariff tariff){
        tariffModel.create(tariff);
        tariffView.showCreated(tariff);
    }
    public void deleteTariffById(int id){
        tariffModel.deleteById(id);
        //tariffView.showDeleted();
    }
    public void editTariff(int id, Tariff tariff){
        tariffModel.updateById(id, tariff);
        //tariffView.showUpdated();
    }

    public void editTariff(int id, double newPrice){
        tariffModel.updateById(id, newPrice);
        //tariffView.showUpdated();
    }

    public void expandBalance(int id, double sum){
        customerModel.updateById(id, sum);
        //customerModel.showUpdated();
    }
    public void banCustomer(int id){
        customerModel.updateById(id, true);
        customerView.showBanned();
    }
    public void unbanCustomer(int id){
        customerModel.updateById(id, false);
        customerView.showUnbanned();
    }
}
