package DB;

import entity.*;

import java.security.Provider;
import java.util.*;

public class Data {
    List<Service> serviceList = new ArrayList<Service>(){
        {
            add(new Service(0, "Інтернет"));
            add(new Service(1, "Кабельне ТБ"));
            add(new Service(2, "IP-TV"));
        }
    };
    List<Customer> customerList = new ArrayList<Customer>(){
        {
            add(new Customer(0, "Ivan", "Sirko", 100, "id1", "1234"));
            add(new Customer(1, "Ivan", "Gonta", 0, "id2", "1234"));
            add(new Customer(2, "Ivan", "Bogun", 4500, "id3", "1234"));
        }
    };
    List<Tariff> tariffList = new ArrayList<Tariff>(){
        {
            add(new Tariff(0, "Блискавиця-інтернетниця", 0, 300));
            add(new Tariff(1, "Гігабіт в подарунок", 0, 150));
            add(new Tariff(2, "Основні канали", 1, 250));
            add(new Tariff(3, "ТБ ХХІІ століття", 2, 500));
        }
    };
    List<SubscribeDoc> subscribeDocList = new ArrayList<>();

    // gets
    public List<Service> getServices(){return serviceList;}
    public List<Customer> getCustomers(){return customerList;}
    public List<Tariff> getTariffs(){return tariffList;}
    public List<SubscribeDoc> getDocs(){return subscribeDocList;}

    //sets
    public void setServiceById(int id, Service service){ serviceList.set(id, service); }
    public void setCustomerById(int id, Customer customer){ customerList.set(id, customer); }
    public void setTariffById(int id, Tariff tariff){ tariffList.set(id, tariff); }
    public void setSubscribeDocById(int id, SubscribeDoc doc){ subscribeDocList.set(id, doc); }

    //adds
    public void addService(Service service){
        service.setId(serviceList.size());
        serviceList.add(service);
    }
    public void addCustomer(Customer customer){
        customer.setId(customerList.size());
        customerList.add(customer);
    }
    public void addTariff(Tariff tariff){
        tariff.setId(tariffList.size());
        tariffList.add(tariff);
    }
    public void addDoc(SubscribeDoc doc){
        doc.setDocId(subscribeDocList.size());
        subscribeDocList.add(doc);
    }

}