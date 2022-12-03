package dao;

import DB.Data;
import entity.SubscribeDoc;

import java.util.List;

public class SubscribeDocDAO implements DAO<SubscribeDoc> {
    private Data data = new Data();

    @Override
    public void create(SubscribeDoc entity) {data.addDoc(entity);}

    @Override
    public List<SubscribeDoc> read() {
        return data.getDocs();
    }

    @Override
    public void update(int id, SubscribeDoc doc) {

    }

    @Override
    public void deleteById(int id) {

    }
}
