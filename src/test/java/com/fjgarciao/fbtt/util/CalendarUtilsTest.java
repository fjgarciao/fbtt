package com.fjgarciao.fbtt.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CalendarUtilsTest {

    @Test
    public void testEasterDate() {
        Calendar e2017 = CalendarUtils.prepareCalendar(2017, Calendar.APRIL, 16);
        Calendar e2018 = CalendarUtils.prepareCalendar(2018, Calendar.APRIL, 1);

        Assert.assertThat(CalendarUtils.getEasterDate(2017).getTime(), is(equalTo(e2017.getTime())));
        Assert.assertThat(CalendarUtils.getEasterDate(2018).getTime(), is(equalTo(e2018.getTime())));
    }

    @Test
    public void testPentecostDate() {
        Calendar p2017 = CalendarUtils.prepareCalendar(2017, Calendar.JUNE, 4);
        Calendar p2018 = CalendarUtils.prepareCalendar(2018, Calendar.MAY, 20);

        Assert.assertThat(CalendarUtils.getPentecostDate(2017).getTime(), is(equalTo(p2017.getTime())));
        Assert.assertThat(CalendarUtils.getPentecostDate(2018).getTime(), is(equalTo(p2018.getTime())));
    }
}