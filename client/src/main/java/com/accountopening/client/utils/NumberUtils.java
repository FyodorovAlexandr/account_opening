package com.accountopening.client.utils;

public class NumberUtils {

    public static boolean getCountsOfDigits(String number){
        if(!number.matches("\\d{10}")){
            return true;
        } else return false;
    }
}
