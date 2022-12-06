import controller.MenuController;
import view.*;
import model.*;
import controller.Controller;
import dao.*;

public class Main {
    public static void main(String[] args) {
        CustomerView cV = new CustomerView();
        ServiceView sV = new ServiceView();
        TariffView tV = new TariffView();
        SubscribeDocView dV = new SubscribeDocView();

        CustomerModel cM = new CustomerModel();
        ServiceModel sM = new ServiceModel();
        TariffModel tM = new TariffModel();
        SubscribeDocModel dM = new SubscribeDocModel();

        Controller controller = new Controller();
        controller.setAllModels(cM, sM, tM, dM);
        controller.setAllViews(cV, sV, tV, dV);

        MenuController menu = new MenuController(controller, "Гвінтік");
        menu.setMenu();

    }
}