package controller;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.*;
import view.*;
import entity.*;

import javax.swing.text.Document;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.*;

import exporter.SQLExporter;

public class Controller {
    private CustomerView customerView;
    private ServiceView serviceView;
    private TariffView tariffView;
    private SubscribeDocView docView;

    private CustomerModel customerModel;
    private ServiceModel serviceModel;
    private TariffModel tariffModel;
    private SubscribeDocModel docModel;


    public void bdToPdf(){
        String path = "jdbc:mysql://127.0.0.1:3306/internetprovider";
        String user = "root";
        String password = "1234";
        SQLExporter exporter = new SQLExporter(path, user, password);
        try {
            exporter.exportToPdf();
        }
        catch (Exception e){
            customerView.showError();
        }
    }

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
        List<Tariff> tariffs = new ArrayList<Tariff>();
        try {
            return tariffModel.getAll();
        }
        catch (SQLException e){
            tariffView.showError();
        }
        return tariffs;
    }

    public List<Customer> returnAllCustomers(){
        List<Customer> customers = new ArrayList<Customer>();
        try {
            return customerModel.getAll();
        }
        catch (SQLException e){
            customerView.showError();
        }
        return customers;
    }

    public Service returnServiceByName(String name){
        try{
            return serviceModel.getByName(name);
        }
        catch (SQLException e){
            serviceView.showError();
        }
        return null;
    }

    public Tariff returnTariffByName(String name){
        try {
            return tariffModel.getByName(name);
        }
        catch (SQLException e){
            tariffView.showError();
        }
        return null;
    }

    public Tariff returnTariffById(int id){
        try {
            return tariffModel.getById(id);
        }
        catch (SQLException e){
            tariffView.showError();
        }
        return null;
    }
    public Customer returnCustomerById(int id){
        try {
            return customerModel.getById(id);
        }
        catch (SQLException e){
            tariffView.showError();
        }
        return null;
    }

    public Customer returnCustomerByLogin(String login){
        try {
            return customerModel.getByLogin(login);
        }
        catch (SQLException e){
            customerView.showError();
        }
        return null;
    }

    public Customer returnCustomerByParol(String parol){
        try {
            return customerModel.getByParol(parol);
        }
        catch (SQLException e){
            customerView.showError();
        }
        return null;
    }

    public void getAllServices(){
        try{
            List<Service> services = serviceModel.getAll();
            serviceView.show(services);
        }
        catch (SQLException e){
            serviceView.showError();
        }
    }
    public void getAllCustomers(){
        try {
            List<Customer> customers = customerModel.getAll();
            customerView.show(customers);
        }
        catch (SQLException e){
            customerView.showError();
        }
    }
    public void getAllTariffs(){
        try{
            List<Tariff> tariffs = tariffModel.getAll();
            tariffView.show(tariffs);
        }
        catch (SQLException e){
            tariffView.showError();
        }
    }
    public void createNewCustomer(Customer customer){
        try{
            customerModel.create(customer);
            customerView.showCreated(customer);
        }
        catch (SQLException e){
            customerView.showError();
        }
    }
    public void getTariffsAlphaSorted(){
        try {
            List<Tariff> tariffs = tariffModel.readTariffsAlphaSorted();
            tariffView.show(tariffs);
        }
        catch (SQLException e){
            tariffView.showError();
        }
    }
    public void getTariffsAlphaRevSorted(){
        try {
            List<Tariff> tariffs = tariffModel.readTariffsAlphaRevSorted();
            tariffView.show(tariffs);
        }
        catch (SQLException e){
            tariffView.showError();
        }
    }

    public void getTariffsPriceSorted(){
        try {
            List<Tariff> tariffs = tariffModel.readTariffsPriceSorted();
            tariffView.show(tariffs);
        }
        catch (SQLException e){
            tariffView.showError();
        }
    }

    public void createNewDoc(Tariff tariff, Customer customer){
        try {
            customerModel.updateById(customer.getId(), -tariff.getPrice());
            Service service = serviceModel.getById(tariff.getServiceId());
            docModel.create(tariff, service, customer);
            if(customerModel.getBalance(customer.getId()) < 0){
                customer.ban();
            }
        }
        catch (SQLException e){
            docView.showError();
        }
    }
    public void createNewTariff(Tariff tariff){
        try {
            tariffModel.create(tariff);
            tariffView.showCreated(tariff);
        }
        catch (SQLException e){
            tariffView.showError();
        }
    }
    public void deleteTariffById(int id){
        try{
            tariffModel.deleteById(id);
            //tariffView.showDeleted();
        }
        catch (SQLException e){
            tariffView.showError();
        }
    }
    public void editTariff(int id, Tariff tariff){
        try {
            tariffModel.updateById(id, tariff);
            //tariffView.showUpdated();
        }
        catch (SQLException e){
            tariffView.showError();
        }
    }

    public void editTariff(int id, double newPrice){
        try {
            tariffModel.updateById(id, newPrice);
            //tariffView.showUpdated();
        }
        catch (SQLException e){
            tariffView.showError();
        }
    }

    public void expandBalance(int id, double sum){
        try{
            customerModel.updateById(id, sum);
            //customerModel.showUpdated();
        }
        catch (SQLException e){
            customerView.showError();
        }
    }
    public void banCustomer(int id){
        try {
            customerModel.updateById(id, true);
            customerView.showBanned();
        }
        catch (SQLException e){
            customerView.showError();
        }
    }
    public void unbanCustomer(int id){
        try {
            customerModel.updateById(id, false);
            customerView.showUnbanned();
        }
        catch (SQLException e){
            customerView.showError();
        }
    }
}