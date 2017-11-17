import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static boolean programRunning = true;
    public static String user = "";

    public static void main(String[] args) throws  Exception {
        System.out.println("Velkommen til Delfinen!\n");

        do { mainMenu(); } while(programRunning);

        System.out.print("Farvel!");
    }

    public static void mainMenu() throws Exception {
        Scanner input = new Scanner(System.in);
        String inputString = "";
        boolean isInvalid;

        //Menu
        System.out.println("1 - Opret nyt medlem");

        do {
            isInvalid = false;

            inputString = input.next();

            if(inputString.equals("1")) {
                createNewMember(input);
            }
        } while (isInvalid);
    }

    public static void createNewMember(Scanner input) throws Exception {
        input = new Scanner(System.in);

        String firstName = returnString(input, "Fornavn", true);
        String lastName = returnString(input, "Efternavn", true);
        String address = returnString(input, "Adresse", false);
        int zipcode = returnInt(input, "Postnummer", false);
        String city = returnString(input, "By", false);
        long birthday = returnLong(input, "Foedselsdato");
        int phone = returnInt(input, "Telefon", true);
        boolean isActive = returnBoolean(input, "Medlemskab - Skriv AKTIV eller PASSIV", "AKTIV", "PASSIV");
        boolean isPassive = !isActive;
        boolean isMotionist = returnBoolean(input, "Aktivitetesform - Skriv MOTIONIST eller KONKURRENCE", "MOTIONIST", "KONKURRENCE");
        boolean isCompetitor = !isMotionist;
        boolean isInArrears = returnBoolean(input, "Har vedkommende betalt kontingent - Skriv JA eller NEJ", "NEJ", "JA");

        new Member(firstName, lastName, address, zipcode, city, birthday, phone, isActive, isPassive, isMotionist, isCompetitor, isInArrears);

        System.out.println("Medlemmet blev oprettet!");
    }

    public static String returnString(Scanner input, String question, boolean onlyLetters) {
        input = new Scanner(System.in);
        boolean isInvalid;
        String returnValue = "";

        System.out.println(question+":");

        do {
            isInvalid = false;

            String string = input.nextLine();

            if(onlyLetters && !string.matches("[a-zA-Z]+")) {
                isInvalid = true;
                System.out.println(question+" må kun bestå af bogstaver fra A-Z, prøv igen!:");
            } else {
                returnValue = string;
            }
        } while(isInvalid);

        return returnValue;
    }

    public static int returnInt(Scanner input, String question, boolean isPhone) {
        input = new Scanner(System.in);
        boolean isInvalid;
        int returnValue = 0;

        System.out.println(question+":");

        do {
            isInvalid = false;

            String string = input.nextLine();

            try {
                int number = Integer.valueOf(string);

                if(isPhone && String.valueOf(number).length() != 8) {
                    isInvalid = true;
                    System.out.println(question+" skal være på 8 cifre, prøv igen:");
                } else if(number < 0) {
                    isInvalid = true;
                    System.out.println(question+" skal være et positivt tal, prøv igen:");
                } else {
                    returnValue = number;
                }
            } catch (Exception e) {
                System.out.println(question+" skal være et tal, prøv igen!");
            }
        } while(isInvalid);

        return returnValue;
    }

    public static long returnLong(Scanner input, String question) {
        input = new Scanner(System.in);
        boolean isInvalid;
        long returnValue = 0;

        System.out.println(question+" - format <DD-MM-YYYY>:");

        do {
            isInvalid = false;

            String string = input.nextLine();

            if (string.length() != 10) {
                isInvalid = true;
                System.out.println(question+" er ikke i den rette format, prøv igen:");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);

                try {
                    returnValue = sdf.parse(string).getTime();

                    if(returnValue > new Date().getTime()) {
                        isInvalid = true;
                        System.out.println("Vedkommende kan ikke være født ude i fremtiden, prøv igen:");
                    }
                } catch (Exception e) {
                    System.out.println(question + " er ikke en gyldig dato, prøv igen!");
                }
            }
        } while(isInvalid);

        return returnValue;
    }

    public static boolean returnBoolean(Scanner input, String question, String option_true, String option_false) {
        input = new Scanner(System.in);
        boolean isInvalid, returnValue = false;

        System.out.println(question+":");

        do {
            isInvalid = false;

            String string = input.nextLine();

            if(string.equalsIgnoreCase(option_true)) {
                returnValue = true;
            } else if(!string.equalsIgnoreCase(option_true) && !string.equalsIgnoreCase(option_false)) {
                isInvalid = true;
                System.out.println("Ikke et gyldig svar, prøv igen:");
            }
        } while(isInvalid);

        return returnValue;
    }
}
