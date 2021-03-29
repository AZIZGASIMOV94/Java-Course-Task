package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String myTxtFile = "C:\\Users\\User\\Desktop\\words.txt";
    private static String langPreference;
    private static Scanner scanner;
    private static String userAnswer;
    private static String langOptionsString;
    private static String errorMessageString;
    private static String questionString;
    private static String successFeedbackString;
    private static String failureString;
    private static Map<String, String> mapFromFile;
    static {
        scanner = new Scanner(System.in);
        langOptionsString = "Sizden lugetdeki sozler sorushulacaq, Ingilis dili uchun \"Eng\", Azerbaycanca uchun \"Aze\" yazisini daxil edin!";
        errorMessageString = "Daxil etdiyiniz dil helelik desdeklenmir!";
        questionString = " tercumesi nedir?";
        successFeedbackString = "Duzgun Cavab, Tebrikler!";
        failureString = "yanlish cavab, hazirki sual uchun son shans!";
        try {
            mapFromFile = hashMapFromTextFile("C:\\Users\\User\\Desktop\\words.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> hashMapFromTextFile(String fileName) throws Exception {
        HashMap<String, String> map = new HashMap();
        FileInputStream in = new FileInputStream(fileName);
        try {
            InputStreamReader r = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(r);
            String line = null;
            String result = "";

            while((line = reader.readLine()) != null) {
                result = result + line + "\n";
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String number = parts[1].trim();
                if (!name.equals("") && !number.equals("")) {
                    map.put(name, number);
                }
            }
            reader.close();
            r.close();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        in.close();
        return map;
    }

    public static void handleEnglishLang() {
        Iterator iterator = mapFromFile.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)iterator.next();

            System.out.println(entry.getKey() + questionString);
            userAnswer = scanner.next();
            if (entry.getValue().equals(userAnswer)) {
                System.out.println(successFeedbackString);
            } else {
                int count = 0;
                if (count < 2) {
                    System.out.println(entry.getKey() + questionString);
                    userAnswer = scanner.next();
                    if (entry.getValue().equals(userAnswer)) {
                        System.out.println(successFeedbackString);
                    } else {
                        count ++;
                        System.out.println(failureString);
                        userAnswer = scanner.next();
                        if (entry.getValue().equals(userAnswer)) {
                            System.out.println(successFeedbackString);
                        }
                    }
                }
            }
        }
    }

    public static void handleAzeLang() {
        Iterator var0 = mapFromFile.entrySet().iterator();

        while(var0.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var0.next();
            PrintStream var10000 = System.out;
            String var10001 = (String)entry.getValue();
            var10000.println(var10001 + questionString);
            userAnswer = scanner.next();
            if (((String)entry.getKey()).equals(userAnswer)) {
                System.out.println(successFeedbackString);
            } else {
                int count = 0;
                if (count < 2) {
                    var10000 = System.out;
                    var10001 = (String)entry.getValue();
                    var10000.println(var10001 + questionString);
                    userAnswer = scanner.next();
                    if (((String)entry.getKey()).equals(userAnswer)) {
                        System.out.println(successFeedbackString);
                    } else {
                        int var3 = count + 1;
                        System.out.println(failureString);
                        userAnswer = scanner.next();
                        if (((String)entry.getKey()).equals(userAnswer)) {
                            System.out.println(successFeedbackString);
                        }
                    }
                }
            }
        }

    }

    public static void handleLangCases(String langPreference) throws Exception {
        byte var2 = -1;
        switch(langPreference.hashCode()) {
            case 66348:
                if (langPreference.equals("Aze")) {
                    var2 = 1;
                }
                break;
            case 69822:
                if (langPreference.equals("Eng")) {
                    var2 = 0;
                }
        }

        switch(var2) {
            case 0:
                handleEnglishLang();
                break;
            case 1:
                handleAzeLang();
                break;
            default:
                System.out.println(errorMessageString);
        }

    }

    public static void main(String[] args) throws Exception{
	    // write your code here
        System.out.println(langOptionsString);
        langPreference = scanner.next();
        handleLangCases(langPreference);
    }
}
