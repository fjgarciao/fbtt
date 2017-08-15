package com.fjgarciao.fbtt.component;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MarketingDayFactory {

    private enum MarketingDays {
        MOTHERS_DAY(new MothersDay());

        private MarketingDay marketingDay;

        MarketingDays(MarketingDay marketingDay) {
            this.marketingDay = marketingDay;
        }

        public MarketingDay getMarketingDay() {
            return marketingDay;
        }
    }

    public List<MarketingDay> getAllMarketingDays() {
        return Arrays.stream(MarketingDays.values()).map(marketingDays -> marketingDays.getMarketingDay()).collect(Collectors.toList());
    }

    public Optional<MarketingDay> getMarketingDayByName(String name) {
        try {
            return Optional.of(MarketingDays.valueOf(name).getMarketingDay());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
