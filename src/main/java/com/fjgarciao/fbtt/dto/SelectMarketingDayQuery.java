package com.fjgarciao.fbtt.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

public class SelectMarketingDayQuery {

    @NotNull
    @Size(min = 1)
    private String marketingDay;

    @NotNull
    @Digits(integer = 4, fraction = 4)
    @Min(1900)
    private int year;

    public SelectMarketingDayQuery() {
        Calendar c = Calendar.getInstance();
        this.year = c.get(Calendar.YEAR);
    }

    public String getMarketingDay() {
        return marketingDay;
    }

    public void setMarketingDay(String marketingDay) {
        this.marketingDay = marketingDay;
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
                "marketingDay='" + marketingDay + '\'' +
                ", year=" + year +
                '}';
    }
}
