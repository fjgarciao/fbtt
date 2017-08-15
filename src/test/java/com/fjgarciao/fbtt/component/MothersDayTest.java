package com.fjgarciao.fbtt.component;

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
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.FEBRUARY, 12);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.FEBRUARY, 11);

        Assert.assertThat(uit.getDate(CountryCode.NO, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.NO, 2018).get(), is(equalTo(d2018.getTime())));
    }

    @Test
    public void testMotheringSunday() {
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.MARCH, 26);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.MARCH, 11);
        Calendar d2019 = CalendarUtils.prepareCalendar(2019, Calendar.MARCH, 31);

        Assert.assertThat(uit.getDate(CountryCode.UK, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.UK, 2018).get(), is(equalTo(d2018.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.UK, 2019).get(), is(equalTo(d2019.getTime())));
    }

    @Test
    public void testSpain() {
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.MAY, 7);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.MAY, 6);

        Assert.assertThat(uit.getDate(CountryCode.ES, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.ES, 2018).get(), is(equalTo(d2018.getTime())));
    }

    @Test
    public void testLastSundayOfMay() {
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.MAY, 28);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.MAY, 27);
        Calendar d2020 = CalendarUtils.prepareCalendar(2020, Calendar.MAY, 31);
        Calendar d2021 = CalendarUtils.prepareCalendar(2021, Calendar.MAY, 30);
        Calendar d2022 = CalendarUtils.prepareCalendar(2022, Calendar.MAY, 29);

        Assert.assertThat(uit.getDate(CountryCode.SE, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.SE, 2018).get(), is(equalTo(d2018.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.SE, 2020).get(), is(equalTo(d2020.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.SE, 2021).get(), is(equalTo(d2021.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.SE, 2022).get(), is(equalTo(d2022.getTime())));
    }
    
    @Test
    public void testFrance() {
        Calendar d2019 = CalendarUtils.prepareCalendar(2019, Calendar.MAY, 26);
        Calendar d2020 = CalendarUtils.prepareCalendar(2020, Calendar.JUNE, 7);

        Assert.assertThat(uit.getDate(CountryCode.FR, 2019).get(), is(equalTo(d2019.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.FR, 2020).get(), is(equalTo(d2020.getTime())));
    }

    @Test
    public void testLuxembourg() {
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.JUNE, 11);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.JUNE, 10);

        Assert.assertThat(uit.getDate(CountryCode.LU, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.LU, 2018).get(), is(equalTo(d2018.getTime())));
    }

    @Test
    public void testSouthSudan() {
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.JULY, 3);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.JULY, 2);

        Assert.assertThat(uit.getDate(CountryCode.SS, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.SS, 2018).get(), is(equalTo(d2018.getTime())));
    }

    @Test
    public void testMalawi() {
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.OCTOBER, 9);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.OCTOBER, 8);

        Assert.assertThat(uit.getDate(CountryCode.MW, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.MW, 2018).get(), is(equalTo(d2018.getTime())));
    }
    
    @Test
    public void testArgentina() {
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.OCTOBER, 15);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.OCTOBER, 21);

        Assert.assertThat(uit.getDate(CountryCode.AR, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.AR, 2018).get(), is(equalTo(d2018.getTime())));
    }

    @Test
    public void testLastSundayOfNovember() {
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.NOVEMBER, 26);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.NOVEMBER, 25);
        Calendar d2019 = CalendarUtils.prepareCalendar(2019, Calendar.NOVEMBER, 24);
        Calendar d2020 = CalendarUtils.prepareCalendar(2020, Calendar.NOVEMBER, 29);
        Calendar d2021 = CalendarUtils.prepareCalendar(2021, Calendar.NOVEMBER, 28);
        Calendar d2022 = CalendarUtils.prepareCalendar(2022, Calendar.NOVEMBER, 27);

        Assert.assertThat(uit.getDate(CountryCode.RU, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.RU, 2018).get(), is(equalTo(d2018.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.RU, 2019).get(), is(equalTo(d2019.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.RU, 2020).get(), is(equalTo(d2020.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.RU, 2021).get(), is(equalTo(d2021.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.RU, 2022).get(), is(equalTo(d2022.getTime())));
    }

    @Test
    public void testIran() {
        Calendar d2017 = CalendarUtils.prepareCalendar(2017, Calendar.MARCH, 20);
        Calendar d2018 = CalendarUtils.prepareCalendar(2018, Calendar.MARCH, 9);
        Calendar d2019 = CalendarUtils.prepareCalendar(2019, Calendar.FEBRUARY, 26);
        Calendar d2020 = CalendarUtils.prepareCalendar(2020, Calendar.FEBRUARY, 15);
        Calendar d2021 = CalendarUtils.prepareCalendar(2021, Calendar.FEBRUARY, 3);

        Assert.assertThat(uit.getDate(CountryCode.IR, 2017).get(), is(equalTo(d2017.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.IR, 2018).get(), is(equalTo(d2018.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.IR, 2019).get(), is(equalTo(d2019.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.IR, 2020).get(), is(equalTo(d2020.getTime())));
        Assert.assertThat(uit.getDate(CountryCode.IR, 2021).get(), is(equalTo(d2021.getTime())));
    }
}