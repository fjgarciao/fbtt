package com.fjgarciao.fbtt.helper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class MarketingDayFactoryTest {

    private MarketingDayFactory uit;

    @Before
    public void setup() {
        uit = new MarketingDayFactory();
    }

    @Test
    public void mothersDayExists() {
        Assert.assertThat(uit.getMarketingDayByName("MOTHERS_DAY").isPresent(), is(true));
    }

}