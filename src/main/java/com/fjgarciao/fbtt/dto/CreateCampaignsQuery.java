package com.fjgarciao.fbtt.dto;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateCampaignsQuery {

    @NotNull
    @Size(min = 1)
    private String campaignName;

    @Size(min = 1)
    private List<String> countries = new ArrayList<>();
    private int startOffsetR;

    @NotNull
    @Min(0)
    private int startOffsetDays;

    private int endOffsetR;

    @NotNull
    @Min(0)
    private int endOffsetDays;

    @NotNull
    @Min(0)
    private long lifeTimeBudget;

    @NotNull
    @Min(13)
    @Max(65)
    private long ageMin;

    @NotNull
    @Min(13)
    @Max(65)
    private long ageMax;

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public List<String> getCountries() {
        return countries;
    }

    public Map<String, Date> parseCountries() {
        return countries.stream()
                .map(it -> it.split("\\|"))
                .collect(Collectors.toMap(it -> it[0], it -> new Date(Long.parseLong(it[1]))));
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public int getStartOffsetR() {
        return startOffsetR;
    }

    public void setStartOffsetR(int startOffsetR) {
        this.startOffsetR = startOffsetR;
    }

    public int getStartOffsetDays() {
        return startOffsetDays;
    }

    public void setStartOffsetDays(int startOffsetDays) {
        this.startOffsetDays = startOffsetDays;
    }

    public int getEndOffsetR() {
        return endOffsetR;
    }

    public void setEndOffsetR(int endOffsetR) {
        this.endOffsetR = endOffsetR;
    }

    public int getEndOffsetDays() {
        return endOffsetDays;
    }

    public void setEndOffsetDays(int endOffsetDays) {
        this.endOffsetDays = endOffsetDays;
    }

    public long getLifeTimeBudget() {
        return lifeTimeBudget;
    }

    public void setLifeTimeBudget(long lifeTimeBudget) {
        this.lifeTimeBudget = lifeTimeBudget;
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
}
