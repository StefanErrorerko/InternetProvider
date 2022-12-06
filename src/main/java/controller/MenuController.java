package controller;

import entity.Customer;
import entity.Service;
import entity.Tariff;
import model.CustomerModel;

import java.util.Scanner;

public class MenuController {
    private Controller controller;
    private String providerName;
    private String currentLogin = "";

    public MenuController(Controller controller, String providerName){
        this.controller = controller;
        this.providerName = providerName;
    }

    public void setMenu(){
        do {
            setAuthMenu();
            if (currentLogin.equals("admin")) {
                setAdminMenu();
            } else {
                setUserMenu();
            }
        } while (currentLogin.equals(""));
    }

    private void setAuthMenu(){
        Scanner sc = new Scanner(System.in);
        String parol, login = "";
        do{
            System.out.println("Вітаємо в гостях у інтернет-провайдера \"" + providerName + "\"");
            System.out.println("Пройдіть авторизацію або уведіть 'exit' задля завершення роботи");
            System.out.println("Введіть ваш логін:");

            login = sc.nextLine();
            if(login.equals("exit")){
                currentLogin = "";
                // НЕ ВИКОРИСТОВУВАТИ НА СЕРВЛЕТ
                System.exit(0);
            }
        } while (!isLoginExists(login) && !login.equals("admin"));
        currentLogin = login;
        if(!login.equals("admin")) {
            do {
                System.out.println("Введіть ваш пароль:");
                parol = sc.nextLine();
                if (login.equals("exit")) {
                    currentLogin = "";
                    // НЕ ВИКОРИСТОВУВАТИ НА СЕРВЛЕТ
                    System.exit(0);
                }
            } while (!isParolExists(parol));
        }
    }

    private void setAdminMenu(){
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.println("Головне меню 'АДМІНІСТРАТОРА' інтернет провадйера \"" + providerName + "\"");
            System.out.println("Оберіть дію увівши відповідну цифру:");
            System.out.println("1. Тарифні плани\n" +
                    "2. Операції над користувачами\n" +
                    "3. Назад");
            try {
                choice = sc.nextInt();
                if (choice == 1) {
                    setAdminTariffMenu();
                    choice = 0;
                }
                else if (choice == 2) {
                    setAdminUserMenu();
                    choice = 0;
                }
                else if(choice == 3){
                    currentLogin = "";
                }
            }
            catch (Exception ex) {
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }

        } while (choice != 1 && choice != 2 && choice != 3);
    }

    private void setAdminTariffMenu(){
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Бажаєте додати або змінити тарифні плани?");
            System.out.println("1. Додати\n" +
                    "2. Змінити\n" +
                    "3. Назад");
            try {
                choice = sc.nextInt();
                if (choice == 1) {
                    setAdminCreateTariff();
                    choice = 0;
                } else if (choice == 2) {
                    setAdminChangeTariff();
                    choice = 0;
                }
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }
        } while (choice != 1 && choice != 2 && choice != 3);
    }

    private void setAdminCreateTariff(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть такі дані про тариф:");
        System.out.println("Назва\n" +
                "Назва послуги\n" +
                "Ціна\n");
        try {
            String tariffName = sc.nextLine();
            String serviceName = sc.nextLine();
            double price = Double.parseDouble(sc.nextLine());
            if(controller.returnServiceByName(serviceName) == null){
                throw new Exception("Item not found");
            }
            int serviceId = controller.returnServiceByName(serviceName).getId();
            Tariff tariff = new Tariff(tariffName, serviceId, price);
            controller.createNewTariff(tariff);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void setAdminChangeTariff(){
        Scanner sc = new Scanner(System.in);

        int tariffId = -1;
        do{
            System.out.println("Наявні тарифні плани:");
            controller.getAllTariffs();

            System.out.println("Оберіть один з наявних планів:");
            try {
                tariffId = sc.nextInt();
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                tariffId = -1;
                sc.next();
            }
        } while (controller.returnTariffById(tariffId) == null);

        int choice = 0;

        do{
            System.out.println("Бажаєте змінити чи видалити цей тарифний план?");
            System.out.println("1. Видалити\n" +
                    "2. Змінити ціну\n" +
                    "3. Нічого");
            try {
                choice = sc.nextInt();
                if (choice == 1) {
                    controller.deleteTariffById(tariffId);
                } else if (choice == 2) {
                    System.out.println("Уведіть нову ціну");
                    String rawString = sc.next();
                    double newPrice = Double.parseDouble(rawString);
                    controller.editTariff(tariffId, newPrice);
                }
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }
        } while(choice != 1 && choice != 2 && choice != 3);
    }

    private void setAdminUserMenu(){
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        do{
            System.out.println("Ви бажаєте...");
            System.out.println("1. Зареєструвати нового користувача\n" +
                    "2. Змінити статус бану користувача\n" +
                    "3. Назад?");
            try {
                choice = sc.nextInt();
                if(choice == 1){
                    setAdminRegisterUserMenu();
                    choice = 0;
                }
                else if (choice == 2){
                    setAdminBanUserMenu();
                    choice = 0;
                }
            }
            catch (Exception ex) {
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }
        } while (choice != 1 && choice != 2 && choice != 3);
    }

    private void setAdminRegisterUserMenu(){
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Для створення нового користувача, введіть його дані:");
            System.out.println("Ім'я" +
                    "Прізвище" +
                    "Логін (окрім 'exit' та 'admin')" +
                    "Пароль");
            String name = sc.nextLine();
            String surname = sc.nextLine();
            String login = sc.nextLine();
            String parol = sc.nextLine();
            if(!isLoginAcceptable(login)){
                throw new Exception("Login is unacceptable or is initialized earlier.");
            }
            Customer customer = new Customer(name, surname, login, parol);
            controller.createNewCustomer(customer);
            System.out.println("Створено!");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void setAdminBanUserMenu(){
        Scanner sc = new Scanner(System.in);


        int customerId = -1;
        do{
            System.out.println("Перелік зареєстрованих користувачів:");
            controller.getAllCustomers();

            System.out.println("Оберіть одного з користувачів, увівши його ІД:");
            try {
                customerId = sc.nextInt();
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                sc.next();
            }
        } while (controller.returnCustomerById(customerId) == null);


        int choice = 0;
        do{
            System.out.println("Бажаєте змінити статус бану користувача чи вас влаштовує його статус?");
            System.out.println("1. Забанити\n" +
                    "2. Розбанити\n" +
                    "3. Лишити як є");
            choice = sc.nextInt();
            try {
                if (choice == 1) {
                    controller.banCustomer(customerId);
                    System.out.println("Успішно забанено");
                } else if (choice == 2) {
                    controller.unbanCustomer(customerId);
                    System.out.println("Успішно розбанено");
                }
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }
        } while (choice != 1 && choice != 2 && choice != 3);
    }

    private void setUserMenu(){
        Scanner sc = new Scanner(System.in);


        int choice = 0;
        do {
            System.out.println("Головне меню інтернет провадйера \"" + providerName + "\"");
            System.out.println("Оберіть дію увівши відповідну цифру:");
            System.out.println("1. Гаманець\n" +
                    "2. Назад\n");
            if(!controller.returnCustomerByLogin(currentLogin).banStatus()){
                System.out.println("3. Переглянути послуги\n" +
                        "4. Обрати тариф");
            }
            try {
                choice = sc.nextInt();
                if (choice == 1) {
                    setUserWallet();
                    choice = 0;
                }
                else if (choice == 2) {
                    currentLogin = "";
                }
                else if (choice == 3){
                    if(!controller.returnCustomerByLogin(currentLogin).banStatus()) {
                        setUserShowService();
                    }
                    choice = 0;
                }
                else if(choice == 4){
                    if(!controller.returnCustomerByLogin(currentLogin).banStatus()) {
                        setUserShowTariff();
                    }
                    choice = 0;
                }
            }
            catch (Exception ex) {
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }

        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);
    }

    private void setUserShowService(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Будь що уведіть, щоб повернутися 'Назад'");
        System.out.println("Доступні послуги від нашого інтернет-провайдера");
        controller.getAllServices();
        String smth = sc.nextLine();
    }

    private void setUserShowTariff(){
        Scanner sc = new Scanner(System.in);


        int choice = 0;
        do{
            System.out.println("Сортувати за...");
            System.out.println("1. алфавітом\n" +
                    "2. алфавітом в зворотньому порядку\n" +
                    "3. ціною");
            try{
                choice = sc.nextInt();
                if(choice == 1){
                    controller.getTariffsAlphaSorted();
                }
                else if(choice == 2){
                    controller.getTariffsAlphaRevSorted();
                }
                else if(choice == 3){
                    controller.getTariffsPriceSorted();
                }
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }
        } while (choice != 1 && choice != 2 && choice != 3);


        choice = 0;
        do{
            System.out.println("Введіть, щоб обрати наступну дію");
            System.out.println("1. Обрати тариф і перейти до покупки\n" +
                    "2. Завантажити тарифи у форматі pdf\n" +
                    "3. Назад\n");
            try{
                choice = sc.nextInt();
                if(choice == 1){
                    setUserChooseTariff();
                }
                else if(choice == 2){
                    controller.bdToPdf();
                    System.out.println("Готово!");
                }
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }

        } while (choice != 1 && choice != 2 && choice != 3);
    }

    private void setUserChooseTariff(){
        Scanner sc = new Scanner(System.in);


        int tariffId = -1;
        do{
            System.out.println("Оберіть один з наявних планів:");
            try {
                tariffId = sc.nextInt();
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                tariffId = -1;
                sc.next();
            }
        } while (controller.returnTariffById(tariffId) == null);


        int choice = 0;
        do{
            System.out.println("Дякую! Ви погодили тариф. Погоджуєтесь на списання коштів?");
            System.out.println("1. Так\n" +
                    "2. Ні");
            try{
                choice = sc.nextInt();
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }
        } while (choice != 1 && choice != 2);
        if(choice == 1){
            if(controller.returnTariffById(tariffId) != null){
                Tariff tariff = controller.returnTariffById(tariffId);
                Customer customer = controller.returnCustomerByLogin(currentLogin);
                controller.createNewDoc(tariff, customer);

                if(customer.banStatus()){
                    System.out.println("Вас було забанено через брак коштів на рахунку. Уведіть будь-що, щоб продовжити");
                    String smth = sc.nextLine();
                }
            }
        }
    }

    private void setUserWallet(){


        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("*Ваш гаманець*");
            if(controller.returnCustomerByLogin(currentLogin).banStatus()){
                System.out.println("Увага! Ви у бані. Щоб розбанитись - поповніть гаманець.");
            }
            System.out.println("Ваш рахунок:" + controller.returnCustomerByLogin(currentLogin).getSum());
            System.out.println("1. Поповнити гаманець\n" +
                    "2. Назад");
            try{
                choice = sc.nextInt();
                if(choice == 1){
                    setUserReplenish();
                }
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                choice = 0;
                sc.next();
            }
        } while (choice != 1 && choice != 2);

    }

    private void setUserReplenish(){
        Scanner sc = new Scanner(System.in);
        double sum = 0;

        do{
            System.out.println("На яку суму бажаєте поповнити?");
            try{
                sum = Double.parseDouble(sc.nextLine());
            }
            catch (Exception ex){
                System.out.println("Введені дані невірні");
                sum = 0;
                sc.next();
            }
        } while (sum <= 0);
        controller.expandBalance(controller.returnCustomerByLogin(currentLogin).getId(), sum);
        System.out.println("Поповнено");
        if(controller.returnCustomerByLogin(currentLogin).getSum() > 0){
            controller.unbanCustomer(controller.returnCustomerByLogin(currentLogin).getId());
        }
    }

    private boolean isLoginAcceptable(String login){
        if(controller.returnCustomerByLogin(login) != null){
            return false;
        }
        if(login.equals("admin")){
            return false;
        }
        if(login.equals("exit")){
            return false;
        }
        return true;
    }

    private boolean isParolExists(String parol){
        if(controller.returnCustomerByParol(parol) != null){
            return true;
        }
        return false;
    }

    private boolean isLoginExists(String login){
        if(controller.returnCustomerByLogin(login) != null){
            return true;
        }
        return false;
    }
}