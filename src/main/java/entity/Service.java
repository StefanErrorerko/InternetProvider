package entity;

public class Service {
    int serviceId;
    String name;

    public Service(int id, String name){
        serviceId = id;
        this.name = name;
    }
    public Service(String stringId, String name){
        serviceId = Integer.parseInt(stringId);
        this.name = name;
    }
    public Service(String name){
        serviceId = -1;
        this.name = name;
    }
    //gets
    public int getId(){ return serviceId; }
    public String getName(){ return name; }

    //sets
    public void setId(int id){ serviceId = id; }
    public void setName(String name){ this.name = name; }
}