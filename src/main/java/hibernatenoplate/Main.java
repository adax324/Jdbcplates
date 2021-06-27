package hibernatenoplate;


import jdbcplate.Parser;


import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static jdbcplate.Parser.scannerParserStringToInt;

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
                case 1 -> listOfWarehouses();
                case 2 -> listOfProducts();
                case 3 -> addWarehouse();
                case 4 -> addProduct();
                case 5 -> deleteWarehouse();
                case 6 -> moveProduct();
                case 7 -> deleteProduct();
                case 8 -> updateCount();
                case 9 -> System.exit(0);
            }
        }
    }

    private static void listOfWarehouses() {
        for (Warehouse warehouse :
                WAREHOUSE_SERVICE.getAllWarehouses()) {
            System.out.println(warehouse);
        }
        WAREHOUSE_SERVICE.closeConnection();
    }

    private static void listOfProducts() {
        for (Product product :
                PRODUCT_SERVICE.getAllProducts()) {
            System.out.println(product);
        }
    }

    private static void addWarehouse() {


        System.out.println("Podaj nazwę");
        String nazwa = scanner.nextLine();
        System.out.println("Podaj adres");
        String adres = scanner.nextLine();
        Warehouse warehouse = new Warehouse(nazwa, UUID.randomUUID().toString(), adres);
        WAREHOUSE_SERVICE.addWarehouse(warehouse);
    }

    private static void addProduct() {


        System.out.println("Podaj nazwe");
        String nazwa = scanner.nextLine();
        System.out.println("Podaj producenta");
        String producent = scanner.nextLine();
        listOfWarehouses();
        System.out.println("Wybierz magazyn");
        int magazyn = Parser.scannerParserStringToInt();
        System.out.println("Podaj ilość");
        int ilosc = scannerParserStringToInt();
        Warehouse warehouse = WAREHOUSE_SERVICE.getWarehouseById(magazyn);

        Product product = new Product(warehouse, nazwa, producent, ilosc);
        PRODUCT_SERVICE.addProduct(product);

    }


    private static void deleteWarehouse() {
        listOfWarehouses();
        int choice = Parser.scannerParserStringToInt();
        Warehouse warehouse = WAREHOUSE_SERVICE.getWarehouseById(choice);
        List<Product> products = PRODUCT_SERVICE.getAllProductsFromWarehouse(warehouse);
        listOfWarehouses();
        System.out.println("Wybierz magazyn do którego maja być przeniesione produkty");
        int choice1 = Parser.scannerParserStringToInt();
        Warehouse warehouse1 = WAREHOUSE_SERVICE.getWarehouseById(choice1);
        WAREHOUSE_SERVICE.addProductsToWarehouse(products, warehouse1);
        PRODUCT_SERVICE.deleteAllProductsFromWarehouse(warehouse);
        WAREHOUSE_SERVICE.deleteWarehouse(warehouse);

    }

    private static void moveProduct() {
        listOfProducts();
        System.out.println("Wybierz produkt");
        int choice = Parser.scannerParserStringToInt();
        Product product = PRODUCT_SERVICE.getProductBy(choice);
        listOfWarehouses();
        System.out.println("Wybierz magazyn");
        int choice1 = Parser.scannerParserStringToInt();
        Warehouse warehouse = WAREHOUSE_SERVICE.getWarehouseById(choice1);
        PRODUCT_SERVICE.moveProductToWarehouse(product, warehouse);
    }


    private static void deleteProduct() {
        listOfProducts();
        System.out.println("Wybierz produkt");
        int choice = Parser.scannerParserStringToInt();
        Product product = PRODUCT_SERVICE.getProductBy(choice);
        PRODUCT_SERVICE.deletProductFromWarehouse(product);
    }

    private static void updateCount() {
        listOfProducts();
        System.out.println("Wybierz produkt");
        int choice = Parser.scannerParserStringToInt();
        Product product = PRODUCT_SERVICE.getProductBy(choice);
        System.out.println("Wybierz ilość");
        int choice1 = Parser.scannerParserStringToInt();
        PRODUCT_SERVICE.updateCount(product, choice1);
    }


}
