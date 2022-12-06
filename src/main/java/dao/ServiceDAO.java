package dao;

import entity.Service;
import DB.Data;

import java.sql.*;
import java.util.*;

public class ServiceDAO implements DAO<Service>{
    private String connectionAddress = "jdbc:mysql://127.0.0.1:3306/internetprovider";
    private String user = "root";
    private String password = "1234";

    @Override
    public void create(Service service) throws SQLException{
        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        String name = service.getName();

        String query = "insert into service (`Name`) " +
                "values ('"+ name +"');";
        int n = st.executeUpdate(query);
        con.close();
    }
    @Override
    public List<Service> read() throws SQLException {
        List<Service> services = new ArrayList<Service>();

        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from service");

        while (rs.next()) {
            Service serviceFromTable = new Service(
                    rs.getString("ServiceId"),
                    rs.getString("Name"));
            services.add(serviceFromTable);
        }
        con.close();
        return services;
    }

    @Override
    public void update(int id, Service service) throws SQLException{

    }

    @Override
    public void deleteById(int id) throws SQLException{

    }
}