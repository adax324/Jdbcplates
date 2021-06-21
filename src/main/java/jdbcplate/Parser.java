package jdbcplate;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int scannerParserStringToInt() {
        String stringScanner;
        boolean isVaild;
        int converted = 0;
        do {

            try {
                stringScanner = SCANNER.nextLine();
                converted = Integer.parseInt(stringScanner);
                isVaild = true;
            } catch (NumberFormatException e) {
                System.out.println("Błąd spróbuj ponownie");

                isVaild = false;
            }
        } while (!isVaild);
        return converted; //null oznacza błąd
    }

    //format dd-MM-yyyy
    public static LocalDate scannerParserStringToDate() {
        LocalDate localDate = null;
        boolean isVaild;
        do {
            try {
                localDate = LocalDate.parse(SCANNER.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                isVaild = true;
            } catch (DateTimeException e) {
                System.err.println("Błąd wprowadź datę ponownie");
                isVaild = false;
            }
        } while (!isVaild);
        return localDate;


    }


}

