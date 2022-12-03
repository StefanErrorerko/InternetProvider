package entity;

public class Tariff {
    int tariffId;
    String name;
    int serviceId;
    double price;

    public Tariff(int id, String name, int sId, double price){
        tariffId = id;
        this.name = name;
        serviceId = sId;
        this.price = price;
    }

    public Tariff(String name, int sId, double price){
        tariffId = -1;
        this.name = name;
        serviceId = sId;
        this.price = price;
    }

    //gets
    public int getId(){ return tariffId; }
    public String getName(){ return name; }
    public int getServiceId(){ return serviceId; }
    public double getPrice(){ return price; }

    //sets
    public void setId(int id){ tariffId = id; }
    public void setName(String name) { this.name = name; }
    public void setServiceId(int id) { serviceId = id; }
    public void setPrice(double price) { this.price = price; }
}
