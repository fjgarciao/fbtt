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

    // Budget
    @NotNull
    @Min(0)
    private long lifeTimeBudget;

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
        this.ageMin = 13;
        this.ageMax = 65;
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

    public long getLifeTimeBudget() {
        return lifeTimeBudget;
    }

    public void setLifeTimeBudget(long lifeTimeBudget) {
        this.lifeTimeBudget = lifeTimeBudget;
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
        return "CreateCampaignQuery{" +
                "campaignName='" + campaignName + '\'' +
                ", ageMin=" + ageMin +
                ", ageMax=" + ageMax +
                ", lifeTimeBudget=" + lifeTimeBudget +
                ", marketingDay='" + marketingDay + '\'' +
                ", year=" + year +
                '}';
    }
}
