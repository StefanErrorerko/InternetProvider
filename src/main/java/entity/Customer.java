package entity;

import java.text.DecimalFormat;

public class Customer {
    int customerId;
    String name;
    String surname;
    double sum;
    String login;
    String parol;
    boolean isBanned = false;

    public Customer(int id, String name, String surname, double sum, String login, String parol){
        customerId = id;
        this.name = name;
        this.surname = surname;
        this.sum = sum;
        this.login = login;
        this.parol = parol;
    }

    public Customer(String name, String surname, String login, String parol){
        customerId = -1;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.parol = parol;
    }

    //gets
    public int getId(){
        return customerId;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public boolean banStatus(){
        return isBanned;
    }
    public double getSum(){
        return sum;
    }
    public String getLogin(){
        return login;
    }
    public String getParol(){
        return parol;
    }

    //sets
    public void setId(int id){ customerId = id; }
    public void setName(String name){ this.name = name; }
    public void setSurname(String surname){ this.surname = surname; }
    public void setLogin(String login){ this.login = login; }
    public void setParol(String parol){ this.parol = parol; }
    public void setSum(double sum){ this.sum = sum; }
    public void ban(){isBanned = true;}
    public void unban(){isBanned = false;}
    public void setBan(boolean isBanned){this.isBanned = isBanned;}

}
