package com.fjgarciao.fbtt.component;

import com.fjgarciao.fbtt.util.CalendarUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AdSetDateCalculatorTest {

    private AdSetDateCalculator uit;

    @Before
    public void setup() {
        uit = new AdSetDateCalculator();
    }

    @Test
    public void testOffset0() {
        Date date = new Date();
        Assert.assertThat(uit.calculate(date, 0, 0), is(equalTo(date)));
    }

    @Test
    public void testBeforeOffset() {
        Calendar calendar = CalendarUtils.prepareCalendar(2017, Calendar.JANUARY, 20);
        Calendar result = CalendarUtils.prepareCalendar(2017, Calendar.JANUARY, 19);

        Assert.assertThat(uit.calculate(calendar.getTime(), 0, 1), is(equalTo(result.getTime())));
    }

    @Test
    public void testAfterOffset() {
        Calendar calendar = CalendarUtils.prepareCalendar(2017, Calendar.JANUARY, 20);
        Calendar result = CalendarUtils.prepareCalendar(2017, Calendar.JANUARY, 21);

        Assert.assertThat(uit.calculate(calendar.getTime(), 1, 1), is(equalTo(result.getTime())));
    }
}