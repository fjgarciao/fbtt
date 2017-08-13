package com.fjgarciao.fbtt.dto;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CreateCampaignsQuery {

    @Size(min = 1)
    private List<String> countries = new ArrayList<>();
    private int startOffsetR;
    private int startOffsetDays;
    private int endOffsetR;
    private int endOffsetDays;

    public List<String> getCountries() {
        return countries;
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

    @Override
    public String toString() {
        return "CreateCampaignsQuery{" +
                "countries=" + countries +
                ", startOffsetR=" + startOffsetR +
                ", startOffsetDays=" + startOffsetDays +
                ", endOffsetR=" + endOffsetR +
                ", endOffsetDays=" + endOffsetDays +
                '}';
    }
}
