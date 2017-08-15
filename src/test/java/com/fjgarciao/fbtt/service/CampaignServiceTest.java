package com.fjgarciao.fbtt.service;

import com.facebook.ads.sdk.APIException;
import com.fjgarciao.fbtt.dto.CreateCampaignsQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CampaignServiceTest {

    @Autowired
    private CampaignService uit;

    @Test
    public void testCreateCampaign() throws APIException {
        uit.createCampaign();
    }

    @Test
    public void testCreateCalendarCampaign() {
        CreateCampaignsQuery query = new CreateCampaignsQuery();
        query.setCampaignName("Test Campaign");
        query.setCountries(Arrays.asList("AF|1488927600000", "AI|1494712800000"));
        query.setStartOffsetR(0);
        query.setStartOffsetDays(7);
        query.setEndOffsetDays(0);
        query.setEndOffsetDays(1);
        query.setAgeMin(18);
        query.setAgeMax(65);
        query.setLifeTimeBudget(700000);

        uit.createCalendarCampaign(query);
    }
}