package view;
import entity.Service;

import java.util.*;

public class ServiceView implements View<Service> {
    @Override
    public void show(List<Service> entities) {
        System.out.println("Services");
        for(Service service : entities){
            System.out.print("Id: ");
            System.out.print(service.getId());
            System.out.print(", Name: ");
            System.out.println(service.getName());
        }
    }

    @Override
    public void showCreated(Service entity) {

    }

    @Override
    public void showDeleted() {

    }

    @Override
    public void showUpdated() {

    }
}
