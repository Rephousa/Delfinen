package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private static BufferedReader bufferedReader;
    private Writer writer;

    //Constructor for creating a new Member and save it to file
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

    //Constuctor for recieving info about member from file
    public Member(int ID) throws Exception {
        this.ID = ID;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File(".member.dat")));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                if(Integer.valueOf(line.substring(0, line.indexOf(" "))) == ID) {
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.firstName = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.lastName = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.address = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.zipcode = Integer.valueOf(line.substring(0, line.indexOf(" ")));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.city = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.birthday = Long.valueOf(line.substring(0, line.indexOf(" ")));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.telephoneNumber = Integer.valueOf(line.substring(0, line.indexOf(" ")));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.isActive = Boolean.valueOf(line.substring(0, line.indexOf(" ")));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.isPassive = Boolean.valueOf(line.substring(0, line.indexOf(" ")));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.isMotionist = Boolean.valueOf(line.substring(0, line.indexOf(" ")));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.isCompetitor = Boolean.valueOf(line.substring(0, line.indexOf(" ")));
                    line = line.substring(line.indexOf(" ")+1, line.length());
                    this.isInArrears = Boolean.valueOf(line.substring(0, line.length()));
                    break;
                }
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
    }

    private void setID() throws Exception {
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

    public void saveChanges() throws Exception {
        String temp = "";

        temp = getContentFromFile();
        int count = 1;

        if(temp.length() != 0) {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("member.dat")), "UTF8"));

                while (temp.charAt(0) == '#') {
                    temp = temp.substring(1, temp.length());

                    if(Integer.valueOf(temp.substring(0, temp.indexOf(" "))) == ID) {
                        writer.write(ID+" "+firstName+" "+lastName+" "+address.replace(" ", "_")+" "+zipcode+" "+city+" "+birthday+" "+telephoneNumber+" "+isActive+" "+isPassive+" "+isMotionist+" "+isCompetitor+" "+isInArrears+(count != Member.countMember() ? "\n" : ""));
                    } else {
                        writer.write(temp.indexOf("#") >= 0 ? temp.substring(0, temp.indexOf("#")) + (count != Member.countMember() ? "\n" : "") : temp.substring(0, temp.length()));
                    }

                    temp = temp.substring(temp.indexOf("#") < 0 ? 0 : temp.indexOf("#"), temp.length());
                    count++;
                }

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

    public void saveNewMember() throws Exception {
        String temp = getContentFromFile();

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("member.dat")), "UTF8"));

            if(temp.length() != 0) {
                while (temp.charAt(0) == '#') {
                    temp = temp.substring(1, temp.length());

                    writer.write(temp.indexOf("#") >= 0 ? temp.substring(0, temp.indexOf("#")) + "\n" : temp.substring(0, temp.length()) + "\n");

                    temp = temp.substring(temp.indexOf("#") < 0 ? 0 : temp.indexOf("#"), temp.length());
                }
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

    public void delete() {
        String temp = getContentFromFile();

        if(temp.length() != 0) {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("member.dat")), "UTF8"));

                while(temp.charAt(0) == '#') {
                    temp = temp.substring(1, temp.length());

                    if(Integer.valueOf(temp.substring(0, temp.indexOf(" "))) != this.ID) {
                        writer.write(temp.indexOf("#") >= 0 ? temp.substring(0, temp.indexOf("#")) + "\n" : temp.substring(0, temp.length()) + "\n");
                    }

                    temp = temp.substring(temp.indexOf("#") < 0 ? 0 : temp.indexOf("#"), temp.length());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(writer != null) {
                        writer.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //TODO Call to delete trainingsresult and stævner
    }

    public static int countMember() {
        int returnValue = 0;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File("member.dat")));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                returnValue++;
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

        return returnValue;
    }

    public static String[] getMemberForRow(int row) {
        String[] returnArray = new String[10];

        try {
            bufferedReader = new BufferedReader(new FileReader(new File("member.dat")));

            String line;
            int rowNum = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if(rowNum == row) {
                    for (int i = 0; i < returnArray.length; i++) {
                        String temp = line.substring(0, line.indexOf(" ") >= 0 ? line.indexOf(" ") : line.length());

                        if(i == 3) {
                            returnArray[i] = String.valueOf(temp).replace("_", " ");
                        } else if(i == 6) {
                            returnArray[i] = String.valueOf(new SimpleDateFormat("dd-MM-yyyy").format(new Date(Long.valueOf(temp))));
                        } else if(i == 8) {
                            returnArray[i] = temp.equals("true") ? "AKTIV" : "PASSIV";
                        } else if(i == 9) {
                            returnArray[i] = temp.equals("true") ? "MOTIONIST" : "KONKURRENCE";
                        } else {
                            returnArray[i] = String.valueOf(temp);
                        }

                        line = line.substring(line.indexOf(" ") >= 0 ? line.indexOf(" ")+1 : line.length(), line.length());
                    }
                }

                rowNum++;
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

        return returnArray;
    }

    public static boolean doesMemberExist(int ID) {
        boolean returnValue = false;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File("member.dat")));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                if(Integer.valueOf(line.substring(0, line.indexOf(" "))) == ID) {
                    returnValue = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public String getContentFromFile() {
        String returnValue = "";

        try {
            bufferedReader = new BufferedReader(new FileReader(new File("member.dat")));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                returnValue += "#" + line;
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

        return returnValue;
    }
}
