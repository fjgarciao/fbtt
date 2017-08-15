package com.fjgarciao.fbtt.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountrySelectionQuery {

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

    public Map<String, Date> parseCountries() {
        return countries.stream()
                .map(it -> it.split("\\|"))
                .collect(Collectors.toMap(it -> it[0], it -> new Date(Long.parseLong(it[1]))));
    }

    @Override
    public String toString() {
        return "CountrySelectionQuery{" +
                "countries=" + countries +
                ", startOffsetR=" + startOffsetR +
                ", startOffsetDays=" + startOffsetDays +
                ", endOffsetR=" + endOffsetR +
                ", endOffsetDays=" + endOffsetDays +
                '}';
    }
}
