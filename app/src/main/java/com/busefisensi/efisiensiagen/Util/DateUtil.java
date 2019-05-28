package com.busefisensi.efisiensiagen.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateToStringDefault(Date date) {
        return (new SimpleDateFormat("dd MMM yyyy")).format(date);
    }

    public static String getHourFromDateString(String date) {
        String[] splitted = date.split(" ");
        return splitted[1];
    }

    public static String getDateFromDateString(String date) {
        String[] splitted = date.split(" ");
        return splitted[0];
    }
}
