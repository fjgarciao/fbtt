package com.fjgarciao.fbtt.helper;

import com.neovisionaries.i18n.CountryCode;

import java.util.Date;
import java.util.Optional;

public interface MarketingDay {

    String getName();
    Optional<Date> getDate(CountryCode countryCode, int year);
}
