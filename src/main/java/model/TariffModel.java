package model;

import entity.*;
import dao.*;
import DB.Data;

import java.util.List;

public class TariffModel implements Model<Tariff> {
    private TariffDAO tariffDAO = new TariffDAO();
    @Override
    public Tariff getById(int id) {
        List<Tariff> tariffs = getAll();
        for(Tariff tariff : tariffs){
            if(tariff.getId() == id){
                return tariff;
            }
        }
        return null;
    }

    public Tariff getByName(String name){
        List<Tariff> tariffs = getAll();
        for(Tariff tariff : tariffs){
            if(tariff.getName().equals(name)){
                return tariff;
            }
        }
        return null;
    }

    @Override
    public List<Tariff> getAll() {
        return tariffDAO.read();
    }

    @Override
    public void updateById(int id, Tariff entity){
        List<Tariff> tariffs = getAll();
        for(Tariff tariff : tariffs){
            if(tariff.getId() == id){
                tariffDAO.update(id, entity);
            }
        }
    }
    public void updateById(int id, double newPrice){
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
    public void create(Tariff tariff) {
        tariffDAO.create(tariff);
        tariff.setId(getAll().get(getAll().size() - 1).getId());
    }

    @Override
    public void deleteById(int id){
        tariffDAO.deleteById(id);
    }

    public List<Tariff> readTariffsAlphaSorted(){
        List<Tariff> tariffs = getAll();
        //sort
        return tariffs;
    }
    public List<Tariff> readTariffsAlphaRevSorted(){
        List<Tariff> tariffs = getAll();
        //sort reversely
        return tariffs;
    }

    public List<Tariff> readTariffsPriceSorted(){
        List<Tariff> tariffs = getAll();
        //sort pricely
        return tariffs;
    }
}
