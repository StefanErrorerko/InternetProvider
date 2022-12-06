package view;

import entity.Customer;
import entity.Tariff;

import java.util.List;

public class TariffView implements View<Tariff> {

    @Override
    public void show(List<Tariff> entities) {
        System.out.println("Customers");
        for(Tariff tariff : entities){
            System.out.print("Id: ");
            System.out.print(tariff.getId());
            System.out.print(", Name: ");
            System.out.println(tariff.getName());
            System.out.print("ServiceId: ");
            System.out.println(tariff.getServiceId());
            System.out.print("Price: ");
            System.out.println(tariff.getPrice());
        }
    }

    @Override
    public void showCreated(Tariff entity) {

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