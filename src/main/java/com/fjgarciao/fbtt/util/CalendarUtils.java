package com.fjgarciao.fbtt.util;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarUtils {

    public static Calendar prepareCalendar(int year) {
        return prepareCalendar(year, TimeZone.getDefault());
    }

    public static Calendar prepareCalendar(int year, TimeZone timeZone) {
        return prepareCalendar(year, Calendar.JANUARY, 1, timeZone);
    }

    public static Calendar prepareCalendar(int year, int month, int date) {
        return prepareCalendar(year, month, date, TimeZone.getDefault());
    }

    public static Calendar prepareCalendar(int year, int month, int date, TimeZone timeZone) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(timeZone);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    public static Calendar getEasterDate(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int n = (h + l - 7 * m + 114) / 31;
        int p = (h + l - 7 * m + 114) % 31;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, n - 1, p + 1);
        return calendar;
    }

    public static Calendar getPentecostDate(int year) {
        Calendar c = getEasterDate(year);
        c.add(Calendar.WEEK_OF_YEAR, 7);
        return c;
    }
}
