import java.io.*;

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
    private BufferedReader bufferedReader;
    private Writer writer;

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
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("member.dat")));

            this.ID = 0;

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.ID = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            }

            this.ID++;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        String temp = "";

        try {
            bufferedReader = new BufferedReader(new FileReader(new File("member.dat")));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                temp += "#" + line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(temp.length() != 0) {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("member.dat")), "UTF8"));

                while (temp.charAt(0) == '#') {
                    temp = temp.substring(1, temp.length());

                    writer.write(temp.indexOf("#") >= 0 ? temp.substring(0, temp.indexOf("#"))+"\n" : temp.substring(0, temp.length())+"\n");

                    temp = temp.substring(temp.indexOf("#") < 0 ? 0 : temp.indexOf("#"), temp.length());
                }

                writer.write(ID+" "+firstName+" "+lastName+" "+address.replace(" ", "_")+" "+zipcode+" "+city+" "+birthday+" "+telephoneNumber+" "+isActive+" "+isPassive+" "+isMotionist+" "+isCompetitor+" "+isInArrears);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
