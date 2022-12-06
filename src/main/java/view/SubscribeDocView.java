package view;

import entity.SubscribeDoc;
import entity.Tariff;

import java.util.*;

public class SubscribeDocView implements View<SubscribeDoc> {
    @Override
    public void show(List<SubscribeDoc> entities) {
        System.out.println("Customers");
        for(SubscribeDoc doc : entities){
            System.out.print("Id: ");
            System.out.print(doc.getDocId());
            System.out.print(", CustomerId: ");
            System.out.println(doc.getCustomerId());
            System.out.print("TariffId: ");
            System.out.println(doc.getTariffId());
            System.out.print("ServiceId: ");
            System.out.println(doc.getServiceId());
        }
    }

    @Override
    public void showCreated(SubscribeDoc entity) {

    }

    @Override
    public void showDeleted() {

    }

    @Override
    public void showUpdated() {

    }

    @Override
    public void showError() {
        System.out.println("Помилка з'єднання з базою даних... Дивно :(");
    }
}