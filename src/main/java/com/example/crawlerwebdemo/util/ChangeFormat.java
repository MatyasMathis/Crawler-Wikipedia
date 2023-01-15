package com.example.crawlerwebdemo.util;

public class ChangeFormat {
    public static long textToInt(String text){
        if(text.equals("")){
            return 0;
        }

        text=text.replace(",","");
        long numberInt=Long.parseLong(text);
        return numberInt;


    }

    public static double textToDouble(String text){
        if(text.length()<=1){
            return 0;
        }
        String newText=text.replace(",","");
        double numberDouble=Double.parseDouble(newText);
        return numberDouble;
    }
    public static String clearString(String text){
        if(text.length()<=1){
            return "";
        }
        else {
            String[] arrOfStr = text.split(" ", 2);
            return arrOfStr[0];
        }
    }

}
