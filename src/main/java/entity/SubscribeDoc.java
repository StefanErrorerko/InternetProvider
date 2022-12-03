package entity;

public class SubscribeDoc {
    int docId;
    int serviceId;
    int tariffId;
    int customerId;

    public SubscribeDoc(int id, int sId, int tId, int cId){
        docId = id;
        serviceId = sId;
        tariffId = tId;
        customerId = id;
    }
    public SubscribeDoc(int sId, int tId, int cId){
        docId = -1;
        serviceId = sId;
        tariffId = tId;
        customerId = cId;
    }

    //gets
    public int getDocId(){return docId;};
    public int getServiceId(){return serviceId;};
    public int getTariffId(){return tariffId;};
    public int getCustomerId(){return customerId;};

    //sets
    public void setDocId(int id){ docId = id; }
    public void setServiceId(int id){ serviceId = id; }
    public void setTariffId(int id){ tariffId = id; }
    public void setCustomerId(int id){ customerId = id; }

}
