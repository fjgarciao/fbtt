package com.fjgarciao.fbtt.helper;

import com.fjgarciao.fbtt.util.CalendarUtils;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MothersDayTest {

    private MothersDay uit;

    @Before
    public void setup() {
        uit = new MothersDay();
    }

    @Test
    public void testNorway() {
        Calendar no2017 = CalendarUtils.prepareCalendar(2017, Calendar.FEBRUARY, 12);
        Calendar no2018 = CalendarUtils.prepareCalendar(2018, Calendar.FEBRUARY, 11);

        Assert.assertThat(uit.getDate(CountryCode.NO, 2017).get(), is(equalTo(no2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.NO, 2018).get(), is(equalTo(no2018.getTime())));
    }

    @Test
    public void testMotheringSunday() {
        Calendar ms2017 = CalendarUtils.prepareCalendar(2017, Calendar.MARCH, 26);
        Calendar ms2018 = CalendarUtils.prepareCalendar(2018, Calendar.MARCH, 11);
        Calendar ms2019 = CalendarUtils.prepareCalendar(2019, Calendar.MARCH, 31);

        Assert.assertThat(uit.getDate(CountryCode.UK, 2017).get(), is(equalTo(ms2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.UK, 2018).get(), is(equalTo(ms2018.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.UK, 2019).get(), is(equalTo(ms2019.getTime())));
    }

    @Test
    public void testSpain() {
        Calendar es2017 = CalendarUtils.prepareCalendar(2017, Calendar.MAY, 7);
        Calendar es2018 = CalendarUtils.prepareCalendar(2018, Calendar.MAY, 6);

        Assert.assertThat(uit.getDate(CountryCode.ES, 2017).get(), is(equalTo(es2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.ES, 2018).get(), is(equalTo(es2018.getTime())));
    }

    @Test
    public void testLastSundayOfMay() {
        Calendar lsm2017 = CalendarUtils.prepareCalendar(2017, Calendar.MAY, 28);
        Calendar lsm2018 = CalendarUtils.prepareCalendar(2018, Calendar.MAY, 27);
        Calendar lsm2020 = CalendarUtils.prepareCalendar(2020, Calendar.MAY, 31);
        Calendar lsm2021 = CalendarUtils.prepareCalendar(2021, Calendar.MAY, 30);
        Calendar lsm2022 = CalendarUtils.prepareCalendar(2022, Calendar.MAY, 29);

        Assert.assertThat(uit.getDate(CountryCode.SE, 2017).get(), is(equalTo(lsm2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.SE, 2018).get(), is(equalTo(lsm2018.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.SE, 2020).get(), is(equalTo(lsm2020.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.SE, 2021).get(), is(equalTo(lsm2021.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.SE, 2022).get(), is(equalTo(lsm2022.getTime())));
    }
    
    @Test
    public void testFrance() {
        Calendar fr2019 = CalendarUtils.prepareCalendar(2019, Calendar.MAY, 26);
        Calendar fr2020 = CalendarUtils.prepareCalendar(2020, Calendar.JUNE, 7);

        Assert.assertThat(uit.getDate(CountryCode.FR, 2019).get(), is(equalTo(fr2019.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.FR, 2020).get(), is(equalTo(fr2020.getTime())));
    }

}