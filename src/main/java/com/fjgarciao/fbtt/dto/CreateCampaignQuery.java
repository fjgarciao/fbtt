package com.fjgarciao.fbtt.dto;

import javax.validation.constraints.*;
import java.util.Calendar;

public class CreateCampaignQuery {

    // Campaign Name
    @NotNull
    @Size(min = 1)
    private String campaignName;

    // Basic Segmentation
    @NotNull
    @Min(13)
    @Max(65)
    private long ageMin;

    @NotNull
    @Min(13)
    @Max(65)
    private long ageMax;

    // Marketing Day Values
    @NotNull
    @Size(min = 1)
    private String marketingDay;

    @NotNull
    @Digits(integer = 4, fraction = 4)
    @Min(1900)
    private int year;

    public CreateCampaignQuery() {
        Calendar c = Calendar.getInstance();
        this.year = c.get(Calendar.YEAR);
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public long getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(long ageMin) {
        this.ageMin = ageMin;
    }

    public long getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(long ageMax) {
        this.ageMax = ageMax;
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
        return "SelectMarketingDayQuery{" +
                "campaignName='" + campaignName + '\'' +
                ", ageMin=" + ageMin +
                ", ageMax=" + ageMax +
                ", marketingDay='" + marketingDay + '\'' +
                ", year=" + year +
                '}';
    }
}
