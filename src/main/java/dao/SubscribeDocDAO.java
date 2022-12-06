package dao;

import DB.Data;
import entity.Service;
import entity.SubscribeDoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscribeDocDAO implements DAO<SubscribeDoc> {
    private String connectionAddress = "jdbc:mysql://127.0.0.1:3306/internetprovider";
    private String user = "root";
    private String password = "1234";

    @Override
    public void create(SubscribeDoc entity) throws SQLException {
        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        String customerId = String.valueOf(entity.getCustomerId());
        String tariffId = String.valueOf(entity.getTariffId());
        String serviceId = String.valueOf(entity.getServiceId());

        String query = "insert into subscribedoc (`CustomerId`, `TariffId`, `ServiceId`) " +
                "values ('"+ customerId +"', '"+ tariffId +"', '"+ serviceId +"');";
        int n = st.executeUpdate(query);
        con.close();
    }

    @Override
    public List<SubscribeDoc> read() throws SQLException {
        List<SubscribeDoc> docs = new ArrayList<SubscribeDoc>();

        Connection con = DriverManager.getConnection(connectionAddress, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from subscribedoc");

        while (rs.next()) {
            SubscribeDoc docFromTable = new SubscribeDoc(
                    rs.getString("DocId"),
                    rs.getString("CustomerId"),
                    rs.getString("TariffId"),
                    rs.getString("ServiceId"));
            docs.add(docFromTable);
        }
        con.close();
        return docs;
    }

    @Override
    public void update(int id, SubscribeDoc doc) throws SQLException{

    }

    @Override
    public void deleteById(int id) throws  SQLException{

    }
}