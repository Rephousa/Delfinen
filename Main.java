import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static boolean programRunning = true;

    public static void main(String[] args) throws  Exception {
        System.out.println("Velkommen til Delfinen!");

        do { mainMenu(); } while(programRunning);

        System.out.print("Lukker og slukker! Farvel!");
    }

    public static void mainMenu() throws Exception {
        Scanner input = new Scanner(System.in);
        String inputString = "";
        boolean isInvalid;

        String[] commands = {"Medlem", "Luk"};
        String[] descriptions = {"Opret, rediger, slet og se oversigter over medlemmere", "Lukker og afslutter programmet"};
        printMenu(commands, descriptions);

        do {
            isInvalid = true;

            inputString = input.next();

            if(inputString.equalsIgnoreCase("medlem")) {
                subMenu(input, "medlem");
            } else if(inputString.equalsIgnoreCase("luk")) {
                programRunning = false;
                isInvalid = false;
            } else {
                System.out.println("Ugyldig kommando!");
            }
        } while (isInvalid);
    }

    public static void subMenu(Scanner input, String name) throws Exception {
        input = new Scanner(System.in);
        String inputString = "";
        boolean isInvalid;

        do {
            isInvalid = true;

            if(name.equals("medlem")) {
                String[] commands = {"Opret", "Rediger", "Slet", "Tilbage"};
                String[] descriptions = {"Opret et nyt medlem", "Rediger et medlem", "Slet et medlem", "Gå tilbage til hovedmenuen"};
                printMenu(commands, descriptions);

                inputString = input.next();

                if (inputString.equalsIgnoreCase("opret")) {
                    createMember(input);
                } else if (inputString.equalsIgnoreCase("rediger")) {
                    editMember(input);
                } else if (inputString.equalsIgnoreCase("slet")) {
                    //TODO
                } else if (inputString.equalsIgnoreCase("tilbage")) {
                    isInvalid = false;
                } else {
                    System.out.println("Ugyldig kommando!");
                }
            } else if(name.equals("træning")) {
                //TODO
            } else if(name.equals("stævne")) {
                //TODO
            }
        } while(isInvalid);
    }

    public static void printMenu(String[] commands, String[] descriptions) {
        System.out.println();
        System.out.printf("%-40s %-40s\n", "Kommando:", "Beskrivelse:");
        System.out.printf("%-40s %-40s\n", "--------", "------------");
        System.out.println();

        for(int i = 0; i < commands.length; i++) {
            System.out.printf("%-40s %-40s\n", commands[i], descriptions[i]);
        }
    }

    public static void createMember(Scanner input) throws Exception {
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

    public static void editMember(Scanner input) throws Exception {
        input = new Scanner(System.in);
        boolean isInvalid, doItAgain;
        int inputInt;

        Table table = new Table(new String[]{"ID:", "Fornavn:", "Efternavn:", "Adresse:", "Postnr:", "By:", "Fødselsdato", "Telefon:", "Medlemskab:", "Type:"}, new int[]{5, 20, 20, 40, 10, 40, 20, 15, 20, 20});
        table.setPadding(0, 1);
        table.setBorder(1, '+');

        for(int i = 1; i <= Member.countMember(); i++) {
            table.row(Member.getMemberForRow(i));
        }

        table.print();
        System.out.println("");

        do{
            isInvalid = false;

            //Get ID
            inputInt = enterID(input, "Indtast vedkommendes ID du ønsker at redigere i");

            Member member = new Member(inputInt);

            do {
                doItAgain = false;

                String[] commands = {"Fornavn", "Efternavn", "Adresse", "Postnr", "By", "Fødselsdato", "Telefon", "Medlemskab", "Type", "Kontingent", "Tilbage"};
                String[] descriptions = {"Ændre fornavn", "Ændre efternavn", "Ændre adresse", "Ændre postnr", "Ændre by", "Ændre fødselsdato", "Ændre telefon", "Ændre medlemskab (Aktiv eller passiv)", "Ændre typee (Motionist eller konkurrence)", "Ændre kontingent (Betalt eller ikke betalt)", "Gå tilbage til menuen"};
                printMenu(commands, descriptions);

                do {
                    isInvalid = false;

                    String inputString = input.nextLine();

                    switch (inputString.toUpperCase()) {
                        case "FORNAVN":
                            member.setFirstName(returnString(input, "Fornavn", true));
                            break;
                        case "EFTERNAVN":
                            member.setLastName(returnString(input, "Efternavn", true));
                            break;
                        case "ADRESSE":
                            member.setAddress(returnString(input, "Adresse", false));
                            break;
                        case "POSTNR":
                            member.setZipcode(returnInt(input, "Postnr", false));
                            break;
                        case "BY":
                            member.setCity(returnString(input, "By", false));
                            break;
                        case "FØDSELSDATO":
                            member.setBirthday(returnLong(input, "Fødselsdato"));
                            break;
                        case "TELEFON":
                            member.setTelephoneNumber(returnInt(input, "Telefon", true));
                            break;
                        case "MEDLEMSKAB":
                            boolean isActive = returnBoolean(input, "Medlemskab - Skriv AKTIV eller PASSIV", "AKTIV", "PASSIV");
                            member.setActive(isActive);
                            member.setPassive(!isActive);
                            break;
                        case "TYPE":
                            boolean isMotionist = returnBoolean(input, "Aktivitetesform - Skriv MOTIONIST eller KONKURRENCE", "MOTIONIST", "KONKURRENCE");
                            member.setMotionist(isMotionist);
                            member.setCompetitor(!isMotionist);
                            break;
                        case "KONTINGENT":
                            member.setInArrears(returnBoolean(input, "Har vedkommende betalt kontingent - Skriv JA eller NEJ", "NEJ", "JA"));
                            break;
                        case "TILBAGE":
                            break;
                        default:
                            System.out.println("Ugyldig kommando, prøv igen:");
                            isInvalid = true;
                    }
                } while (isInvalid);

                if(returnBoolean(input, "Vil du redigere mere for dett medlem? Skriv JA eller NEJ", "JA", "NEJ")) {
                    doItAgain = true;
                }
            } while(doItAgain);

            member.saveChanges();
            System.out.println("Ændringerne blev gemt!");
        } while(isInvalid);
    }

    public static int enterID(Scanner input, String question) {
        input = new Scanner(System.in);
        boolean isInvalid;
        int returnInt;

        do {
            isInvalid = false;

            returnInt = returnInt(input, question, false);

            if(!Member.doesMemberExist(returnInt)) {
                System.out.println("Den indtastede ID eksisterer ikke, prøv igen:");
                isInvalid = true;
            }
        } while(isInvalid);

        return returnInt;
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
                isInvalid = true;
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
