package com.fjgarciao.fbtt.service;

import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdSet;
import com.facebook.ads.sdk.Campaign;
import com.fjgarciao.fbtt.dto.CreateCampaignsQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CampaignServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignServiceTest.class);

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
        query.setCountries(Arrays.asList("AR|1508018400000", "BY|1507932000000", "ID|1513897200000", "MW|1507500000000", "PA|1512687600000", "RU|1511650800000", "TL|1509663600000"));
        query.setStartOffsetR(0);
        query.setStartOffsetDays(7);
        query.setEndOffsetDays(0);
        query.setEndOffsetDays(1);
        query.setAgeMin(18);
        query.setAgeMax(65);
        query.setLifeTimeBudget(700000);

        uit.createCalendarCampaign(query).ifPresent(campaignId -> {
            final String[] campaignFields = {"id", "name", "configured_status", "created_time", "objective", "start_time", "stop_time"};
            uit.getCampaignData(campaignId, campaignFields).ifPresent(campaign -> {
                LOGGER.debug("Generated Campaign: {}", campaign);
                final String[] adSetFields = {"id", "name", "optimization_goal", "promoted_object", "billing_event", "campaign_id", "configured_status", "created_time", "start_time", "end_time", "lifetime_budget", "targeting"};
                uit.getCampaignAdSets(campaign.getId(), adSetFields).ifPresent(list -> {
                    list.stream().forEach(adSet -> LOGGER.debug("AdSet: {}", adSet));
                });
            });
        });
    }
}