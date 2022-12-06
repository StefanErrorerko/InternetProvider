package dao;

import entity.Customer;
import entity.Service;
import entity.Tariff;

import DB.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffDAO implements DAO<Tariff> {

    private String connectionAddress = "jdbc:mysql://127.0.0.1:3306/internetprovider";
    private String user = "root";
    private String password = "1234";

    @Override
    public void create(Tariff tariff) throws SQLException {
        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        String name = tariff.getName();
        String price = String.valueOf(tariff.getPrice());
        String serviceId = String.valueOf(tariff.getServiceId());

        String query = "insert into tariff (`Name`, `Price`, `ServiceId`) " +
                "values ('"+name+"', '"+price+"', '"+serviceId+"');";
        int n = st.executeUpdate(query);
        con.close();
    }

    @Override
    public List<Tariff> read() throws SQLException {
        List<Tariff> tariffs = new ArrayList<Tariff>();

        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        String query = "select * from tariff";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Tariff tariffFromTable = new Tariff(
                    rs.getString("TariffId"),
                    rs.getString("Name"),
                    rs.getString("Price"),
                    rs.getString("ServiceId"));
            tariffs.add(tariffFromTable);
        }
        con.close();
        return tariffs;
    }

    @Override
    public void update(int id, Tariff tariff) throws SQLException{
        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();

        String stringId = String.valueOf(id);
        String name = tariff.getName();
        String price = String.valueOf(tariff.getPrice());
        String serviceId = String.valueOf(tariff.getServiceId());

        String query = "update tariff " +
                "set Name = '"+name+"', " +
                "Price = "+price+", " +
                "ServiceId = "+serviceId+" " +
                "where TariffId = "+ stringId +";";
        int n = st.executeUpdate(query);
        con.close();
    }

    @Override
    public void deleteById(int id) throws SQLException{
        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        String stringId = String.valueOf(id);

        String query = "delete from tariff " +
                "where TariffId = "+ stringId +";";
        int n = st.executeUpdate(query);
        con.close();
    }
}