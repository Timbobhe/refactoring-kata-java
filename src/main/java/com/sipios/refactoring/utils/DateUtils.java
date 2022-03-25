package com.sipios.refactoring.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    private DateUtils() {
        //ignore
    }

    private static Calendar getCalandarDate(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        Calendar calDate = Calendar.getInstance();
        calDate.setTime(date);
        return calDate;
    }

    public static boolean isSummerDiscountsPeriod(Date date) {
        Calendar cal = getCalandarDate(date);
        return cal.get(Calendar.DAY_OF_MONTH) < 15 && cal.get(Calendar.DAY_OF_MONTH) > 5 && cal.get(Calendar.MONTH) == 5;
    }

    public static boolean isWinterDiscountsPeriod(Date date) {
        Calendar cal = getCalandarDate(date);
        return cal.get(Calendar.DAY_OF_MONTH) < 15 && cal.get(Calendar.DAY_OF_MONTH) > 5 && cal.get(Calendar.MONTH) == 0;
    }
}
