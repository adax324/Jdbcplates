package zadanieutrwalajacejdbc;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static zadanieutrwalajacejdbc.Parser.scannerParserStringToInt;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final WarehouseService WAREHOUSE_SERVICE = new WarehouseService();
    public static final ProductService PRODUCT_SERVICE = new ProductService();

    public static void main(String[] args) {
        startConsoleProgram();
    }

    private static void startConsoleProgram() {


        while (true) {
            System.out.println("""
                    1.Lista magazynów
                    2.Produkty
                    3.Dodaj magazyn
                    4.Dodaj produkt
                    5.Usuń magazyn
                    6.Przenieś produkt
                    7.Usuń produkty
                    8.Akutalizuj stan
                    9.Wyjdź""");


            int wybor = scannerParserStringToInt();
            switch (wybor) {
                case 1 -> printAllWerehouses();
                case 2 -> printAllProducts();
                case 3 -> addWarehouse();
                case 4 -> addProduct();
                case 5 -> deleteWarehouse();
                case 6 -> moveProduct();
                case 7 -> removeProduct();
                case 8 -> updateCount();
                case 9 -> System.exit(0);
            }
        }
    }

    private static void printAllWerehouses() {
        List<Warehouse> warehouses = WAREHOUSE_SERVICE.getAllWerehouses();
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse);
        }

    }

    private static void printAllProducts() {
        List<Product> products = PRODUCT_SERVICE.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void addWarehouse() {
        System.out.println("Podaj nazwe");
        String nazwa = scanner.nextLine();

        System.out.println("Podaj adres");
        String adres = scanner.nextLine();


        WAREHOUSE_SERVICE.addWerehouse(nazwa, UUID.randomUUID().toString(), adres);
    }

    private static void addProduct() {
        System.out.println("Wybierz magazyn");
        List<Warehouse> warehouses = WAREHOUSE_SERVICE.getAllWerehouses();
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse);
        }
        int choiceWarehouse = scannerParserStringToInt();

        System.out.println("Wpisz nazwę");
        String name = scanner.nextLine();
        System.out.println("Wpisz producenta");
        String producent = scanner.nextLine();
        System.out.println("Wpisz ilość");
        int count = scannerParserStringToInt();
        Product product = new Product(choiceWarehouse, name, producent, count);
        PRODUCT_SERVICE.addProduct(product);
    }

    private static void deleteWarehouse() {
        printAllWerehouses();
        System.out.println("Wybierz magazyn do usunięcia");
        int choice = scannerParserStringToInt();
        if (PRODUCT_SERVICE.getProductsByWarehouse(choice).size() == 0) {
            WAREHOUSE_SERVICE.deleteWarehouseById(choice);
        } else {
            System.out.println("Wybierz do którego magazynu przenieść itemy");
            int choice1 = scannerParserStringToInt();

            List<Product> products = PRODUCT_SERVICE.getProductsByWarehouse(choice);
            PRODUCT_SERVICE.addProductsToWarehouse(products, choice1);
            PRODUCT_SERVICE.deleteAllProductsFromWarehouse(choice);
            WAREHOUSE_SERVICE.deleteWarehouseById(choice);
        }
    }

    private static void moveProduct() {
        System.out.println("Wybierz produkt");
        printAllProducts();
        int choice = scannerParserStringToInt();
        System.out.println("Wybierz magazyn");
        printAllWerehouses();
        int choice1 = scannerParserStringToInt();
        PRODUCT_SERVICE.moveProduct(choice, choice1);
        System.out.println("Gotowe!");
    }

    private static void removeProduct() {
        System.out.println("Wybierz produkt do usunięcia");
        printAllProducts();
        int choice = scannerParserStringToInt();
        PRODUCT_SERVICE.deleteProductFromWarehouse(choice);
    }

    private static void updateCount() {
        System.out.println("Wybierz produkt");
        printAllProducts();
        int choice = scannerParserStringToInt();
        System.out.println("Wybierz ilosc");
        int choice1 = scannerParserStringToInt();
        PRODUCT_SERVICE.updateCountOfProduct(choice, choice1);
    }
}









