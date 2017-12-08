package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.company.FileHelper.getContentFromFile;

public class Event {
    private int ID;
    private int fk_memberID;
    private String name;
    private String rank;
    private double time;
    private static BufferedReader bufferedReader;
    private Writer writer;

    public Event(int fk_memberID, String name, String rank, double time) throws Exception {
        setID();

        this.fk_memberID = fk_memberID;
        this.name = name.replace(" ", "_");
        this.rank = rank;
        this.time = time;

        saveNewEvent();
    }

    private void setID() throws Exception {
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("./resources/event.dat")));

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void saveNewEvent() throws Exception {
        String temp = getContentFromFile("./resources/event.dat");

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("./resources/event.dat")), "UTF8"));

            if(temp.length() != 0) {
                while (temp.charAt(0) == '#') {
                    temp = temp.substring(1, temp.length());

                    writer.write(temp.indexOf("#") >= 0 ? temp.substring(0, temp.indexOf("#")) + "\n" : temp.substring(0, temp.length()) + "\n");

                    temp = temp.substring(temp.indexOf("#") < 0 ? 0 : temp.indexOf("#"), temp.length());
                }
            }

            writer.write(ID+" "+fk_memberID+" "+name+" "+rank+" "+time);
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

    public static String[] getEventForRow(int row) throws Exception {
        String[] returnArray = new String[5];

        try {
            bufferedReader = new BufferedReader(new FileReader(new File("./resources/event.dat")));

            String line;
            int rowNum = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if(rowNum == row) {
                    returnArray[0] = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ") + 1, line.length());
                    returnArray[1] = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ") + 1, line.length());
                    returnArray[2] = line.substring(0, line.indexOf(" ")).replace("_", " ");
                    line = line.substring(line.indexOf(" ") + 1, line.length());
                    returnArray[3] = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ") + 1, line.length());
                    returnArray[4] = line.substring(0, line.length());
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

    public static int countEvent() {
        int returnValue = 0;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File("./resources/event.dat")));

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
}
