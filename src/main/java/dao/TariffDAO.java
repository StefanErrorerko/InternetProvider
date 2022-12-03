package dao;

import entity.Service;
import entity.Tariff;

import DB.Data;

import java.util.ArrayList;
import java.util.List;

public class TariffDAO implements DAO<Tariff> {
    private Data data = new Data();
    @Override
    public void create(Tariff entity) {data.addTariff(entity);}

    @Override
    public List<Tariff> read() {return data.getTariffs();}

    @Override
    public void update(int id, Tariff tariff) {

    }

    @Override
    public void deleteById(int id) {
        data.getTariffs().remove(id);
    }
}
