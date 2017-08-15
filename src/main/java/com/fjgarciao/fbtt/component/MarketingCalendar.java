package com.fjgarciao.fbtt.component;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MarketingCalendar {

    /**
     * Returns the available countries and dates from the marketing day
     * @param marketingDay
     * @param year
     * @param keepOldDates
     * @return
     */
    public Map<CountryCode, Date> getValuesFromMarketingDay(MarketingDay marketingDay, int year, boolean keepOldDates) {
        Date now = new Date();

        return Arrays.stream(CountryCode.values())
                .sorted(Comparator.comparing(CountryCode::getName))
                .map(countryCode -> new AbstractMap.SimpleEntry<>(countryCode, marketingDay.getDate(countryCode, year)))
                .filter(entry -> entry.getValue().isPresent() && (keepOldDates || entry.getValue().get().after(now)))
                .collect(Collectors.toMap(it -> it.getKey(), it -> it.getValue().get(), (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
