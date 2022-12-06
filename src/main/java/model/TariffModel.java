package model;

import entity.*;
import dao.*;
import DB.Data;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TariffModel implements Model<Tariff> {
    private TariffDAO tariffDAO = new TariffDAO();
    @Override
    public Tariff getById(int id) throws SQLException {
        List<Tariff> tariffs = getAll();
        for(Tariff tariff : tariffs){
            if(tariff.getId() == id){
                return tariff;
            }
        }
        return null;
    }

    public Tariff getByName(String name) throws SQLException{
        List<Tariff> tariffs = getAll();
        for(Tariff tariff : tariffs){
            if(tariff.getName().equals(name)){
                return tariff;
            }
        }
        return null;
    }

    @Override
    public List<Tariff> getAll() throws SQLException{
        return tariffDAO.read();
    }

    @Override
    public void updateById(int id, Tariff entity) throws SQLException{
        List<Tariff> tariffs = getAll();
        for(Tariff tariff : tariffs){
            if(tariff.getId() == id){
                tariffDAO.update(id, entity);
            }
        }
    }
    public void updateById(int id, double newPrice) throws SQLException{
        Tariff newTariff;
        List<Tariff> tariffs = getAll();
        for(Tariff tariff : tariffs){
            if(tariff.getId() == id){
                newTariff = tariff;
                newTariff.setPrice(newPrice);
                updateById(id, newTariff);
            }
        }
    }

    @Override
    public void create(Tariff tariff) throws SQLException{
        tariffDAO.create(tariff);
        tariff.setId(getAll().get(getAll().size() - 1).getId());
    }

    @Override
    public void deleteById(int id) throws SQLException{
        tariffDAO.deleteById(id);
    }

    public List<Tariff> readTariffsAlphaSorted() throws SQLException{
        List<Tariff> tariffs = getAll();
        for (int i = 0; i < tariffs.size() - 1; i++) {
            for (int j = 0; j < tariffs.size() - i - 1; j++) {
                if (tariffs.get(j).getName().compareTo(tariffs.get(j + 1).getName()) > 0) {
                    Tariff temp = tariffs.get(j);
                    tariffs.set(j, tariffs.get(j + 1));
                    tariffs.set(j+1, temp);
                }
            }
        }
        return tariffs;
    }
    public List<Tariff> readTariffsAlphaRevSorted() throws SQLException{
        List<Tariff> tariffs = getAll();
        for (int i = 0; i < tariffs.size() - 1; i++) {
            for (int j = 0; j < tariffs.size() - i - 1; j++) {
                if (tariffs.get(j).getName().compareTo(tariffs.get(j + 1).getName()) < 0) {
                    Tariff temp = tariffs.get(j);
                    tariffs.set(j, tariffs.get(j + 1));
                    tariffs.set(j+1, temp);
                }
            }
        }
        return tariffs;
    }

    public List<Tariff> readTariffsPriceSorted() throws  SQLException{
        List<Tariff> tariffs = getAll();
        for (int i = 0; i < tariffs.size() - 1; i++) {
            for (int j = 0; j < tariffs.size() - i - 1; j++) {
                if (tariffs.get(j).getPrice() > tariffs.get(j + 1).getPrice()) {
                    Tariff temp = tariffs.get(j);
                    tariffs.set(j, tariffs.get(j + 1));
                    tariffs.set(j+1, temp);
                }
            }
        }
        return tariffs;
    }
}