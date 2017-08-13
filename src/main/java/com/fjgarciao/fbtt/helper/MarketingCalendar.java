package com.fjgarciao.fbtt.helper;

import com.neovisionaries.i18n.CountryCode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MarketingCalendar {

    /*
    public Map<CountryCode, Date> getValuesFromMarketingDay(MarketingDay marketingDay, int year) {
        Map<CountryCode, Date> result = new HashMap<>();
        for(CountryCode countryCode : CountryCode.values()) {
            Optional<Date> date = marketingDay.getDate(countryCode, year);
            if (date.isPresent()) {
                result.put(countryCode, date.get());
            }
        }
        return result;
    }
    */

    public Map<CountryCode, Date> getValuesFromMarketingDay(MarketingDay marketingDay, int year) {
        return Arrays.stream(CountryCode.values())
                .map(countryCode -> new AbstractMap.SimpleEntry<>(countryCode, marketingDay.getDate(countryCode, year)))
                .filter(entry -> entry.getValue().isPresent())
                .collect(Collectors.toMap(it -> it.getKey(), it -> it.getValue().get()));
    }

}
