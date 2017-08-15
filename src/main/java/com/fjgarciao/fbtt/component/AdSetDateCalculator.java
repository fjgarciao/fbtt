package com.fjgarciao.fbtt.component;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class AdSetDateCalculator {

    public Date calculate(Date baseDate, int offsetR, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(baseDate);
        c.add(Calendar.DATE, offsetR == 0 ? -days : days);
        return c.getTime();
    }
}
