package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileHelper {
    private static BufferedReader bufferedReader;

    public static String getContentFromFile(String path) throws Exception {
        String returnValue = "";

        try {
            bufferedReader = new BufferedReader(new FileReader(new File(path)));

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

    public static boolean doesIDExist(String path, int ID) throws Exception {
        boolean returnValue = false;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File(path)));

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
}
