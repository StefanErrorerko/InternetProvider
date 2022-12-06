package dao;

import entity.Customer;
import DB.Data;
import entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements DAO<Customer>{
    private String connectionAddress = "jdbc:mysql://127.0.0.1:3306/internetprovider";
    private String user = "root";
    private String password = "1234";

    @Override
    public void create(Customer customer) throws SQLException {
        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        String name = customer.getName();
        String surname = customer.getSurname();
        String sum = String.valueOf(customer.getSum());
        String login = customer.getLogin();
        String parol = customer.getParol();
        Boolean isBanned = customer.banStatus();

        String query = "insert into customer (`Name`, `Surname`, `Sum`, `Login`, `Parol`, `isBanned`) " +
                "values ('"+name+"', '"+surname+"', "+sum+", '"+login+"', '"+parol+"', "+isBanned+");";
        int n = st.executeUpdate(query);
        con.close();
    }
    @Override
    public List<Customer> read() throws SQLException{
        List<Customer> customers = new ArrayList<Customer>();

        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        String query = "select * from customer";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Customer customerFromTable = new Customer(
                    rs.getString("CustomerId"),
                    rs.getString("Name"),
                    rs.getString("Surname"),
                    rs.getString("Sum"),
                    rs.getString("Login"),
                    rs.getString("Parol"),
                    rs.getString("isBanned"));
            customers.add(customerFromTable);
        }
        con.close();
        return customers;
    }
    @Override
    public void update(int id, Customer customer) throws SQLException {

        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        String newSum = String.valueOf(customer.getSum());
        boolean newBanStatus = customer.banStatus();
        String Id = String.valueOf(id);
        String query = "update customer " +
                "set Sum = "+ newSum +", " +
                "isBanned ="+ newBanStatus +" " +
                "where CustomerId = "+ Id +";";
        int n = st.executeUpdate(query);

        con.close();
    }
    @Override
    public void deleteById(int id) throws SQLException {

    }
}