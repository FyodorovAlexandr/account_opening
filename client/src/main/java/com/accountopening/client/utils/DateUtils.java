package com.accountopening.client.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date convertToDatabaseColumn(String str) {
        if (str == null) {
            return null;
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
        try {
            data = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String convertToEntityAttribute(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
