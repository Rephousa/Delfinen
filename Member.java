import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Member {
    private int ID;
    private String firstName;
    private String lastName;
    private String address;
    private int zipcode;
    private String city;
    private long birthday;
    private int telephoneNumber;
    private boolean isActive;
    private boolean isPassive;
    private boolean isMotionist;
    private boolean isCompetitor;
    private boolean isInArrears;
    private Scanner input;

    public Member(String firstName, String lastName, String address, int zipcode, String city, long birthday, int telephoneNumber, boolean isActive, boolean isPassive, boolean isMotionist, boolean isCompetitor, boolean isInArrears) throws Exception {
        setID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.birthday = birthday;
        this.telephoneNumber = telephoneNumber;
        this.isActive = isActive;
        this.isPassive = isPassive;
        this.isMotionist = isMotionist;
        this.isCompetitor = isCompetitor;
        this.isInArrears = isInArrears;

        saveNewMember();
    }

    public void setID() throws Exception {
        input = new Scanner(new File("./resources/member.dat"));

        this.ID = 0;

        while(input.hasNextLine()) {
            this.ID = input.nextInt();

            input.nextLine();
        }

        this.ID++;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public boolean isInArrears() {
        return isInArrears;
    }

    public void setInArrears(boolean inArrears) {
        isInArrears = inArrears;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isPassive() {
        return isPassive;
    }

    public void setPassive(boolean passive) {
        isPassive = passive;
    }

    public boolean isMotionist() {
        return isMotionist;
    }

    public void setMotionist(boolean motionist) {
        isMotionist = motionist;
    }

    public boolean isCompetitor() {
        return isCompetitor;
    }

    public void setCompetitor(boolean competitor) {
        isCompetitor = competitor;
    }

    public void saveNewMember() throws Exception {
        input = new Scanner(new File("./resources/member.dat"));
        String temp = "";

        while(input.hasNextLine()) {
            temp += "#"+input.nextInt()+" "+input.next()+" "+input.next()+" "+input.next()+" "+input.nextInt()+" "+input.next()+" "+input.nextLong()+" "+input.nextInt()+" "+input.nextBoolean()+" "+input.nextBoolean()+" "+input.nextBoolean()+" "+input.nextBoolean()+" "+input.nextBoolean();
        }

        PrintStream printStream = new PrintStream(new File("./resources/member.dat"));

        if(temp.length() != 0) {
            while (temp.charAt(0) == '#') {
                temp = temp.substring(1, temp.length());

                printStream.println(temp.indexOf("#") >= 0 ? temp.substring(0, temp.indexOf("#")) : temp.substring(0, temp.length()));

                temp = temp.substring(temp.indexOf("#") < 0 ? 0 : temp.indexOf("#"), temp.length());
            }
        }

        printStream.print(ID+" "+firstName+" "+lastName+" "+address.replace(" ", "_")+" "+zipcode+" "+city+" "+birthday+" "+telephoneNumber+" "+isActive+" "+isPassive+" "+isMotionist+" "+isCompetitor+" "+isInArrears);
    }
}
