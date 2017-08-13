package com.fjgarciao.fbtt.helper;

import com.fjgarciao.fbtt.util.CalendarUtils;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MarketingCalendarTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketingCalendarTest.class);

    private MarketingCalendar uit;

    @Before
    public void setup() {
        uit = new MarketingCalendar();
    }

    @Test
    public void testMothersDayMarketingCalendarData() {
        Map<CountryCode, Date> mothersDayValues = uit.getValuesFromMarketingDay(new MothersDay(), 2017);

        Assert.assertThat(mothersDayValues.get(CountryCode.NO), is(equalTo(CalendarUtils.prepareCalendar(2017, Calendar.FEBRUARY, 12).getTime())));
        Assert.assertThat(mothersDayValues.get(CountryCode.UK), is(equalTo(CalendarUtils.prepareCalendar(2017, Calendar.MARCH, 26).getTime())));
        Assert.assertThat(mothersDayValues.get(CountryCode.ES), is(equalTo(CalendarUtils.prepareCalendar(2017, Calendar.MAY, 7).getTime())));
        Assert.assertThat(mothersDayValues.get(CountryCode.SE), is(equalTo(CalendarUtils.prepareCalendar(2017, Calendar.MAY, 28).getTime())));

        //LOGGER.debug("Mother's Days Values: {}", mothersDayValues);
    }

}