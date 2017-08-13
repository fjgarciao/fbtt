package com.fjgarciao.fbtt.dto;

import javax.validation.constraints.NotNull;
import java.util.Calendar;

public class SelectMarketingDayQuery {

    @NotNull
    private String calendarName;
    private int year;

    public SelectMarketingDayQuery() {
        Calendar c = Calendar.getInstance();
        this.year = c.get(Calendar.YEAR);
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "CalendarQuery{" +
                "calendarName='" + calendarName + '\'' +
                ", year=" + year +
                '}';
    }
}
