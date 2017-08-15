package com.fjgarciao.fbtt.service;

import com.facebook.ads.sdk.APIException;
import com.fjgarciao.fbtt.dto.CountrySelectionQuery;
import com.fjgarciao.fbtt.dto.CreateCampaignQuery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CampaignServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignServiceTest.class);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final JsonParser parser = new JsonParser();

    @Autowired
    private CampaignService uit;

    @Test
    public void testCreateCalendarCampaign() {
        CreateCampaignQuery createCampaignQuery = new CreateCampaignQuery();
        createCampaignQuery.setCampaignName("Test Campaign");
        createCampaignQuery.setAgeMin(18);
        createCampaignQuery.setAgeMax(65);
        createCampaignQuery.setLifeTimeBudget(700000);

        CountrySelectionQuery countrySelectionQuery = new CountrySelectionQuery();
        countrySelectionQuery.setCountries(Arrays.asList("AR|1508018400000", "BY|1507932000000", "ID|1513897200000", "MW|1507500000000", "PA|1512687600000", "RU|1511650800000", "TL|1509663600000"));
        countrySelectionQuery.setStartOffsetR(0);
        countrySelectionQuery.setStartOffsetDays(7);
        countrySelectionQuery.setEndOffsetDays(0);
        countrySelectionQuery.setEndOffsetDays(1);

        uit.createCalendarCampaign(createCampaignQuery, countrySelectionQuery).ifPresent(campaignId -> {
            final String[] campaignFields = {"id", "name", "configured_status", "created_time", "objective", "start_time", "stop_time"};
            uit.getCampaign(campaignId, campaignFields).ifPresent(campaign -> {
                LOGGER.info("Generated Campaign: {}", gson.toJson(parser.parse(campaign.getRawValue())));
                final String[] adSetFields = {"id", "name", "optimization_goal", "promoted_object", "billing_event", "campaign_id", "configured_status", "created_time", "start_time", "end_time", "lifetime_budget", "targeting"};
                uit.getCampaignAdSets(campaign.getId(), adSetFields).ifPresent(adSets -> {
                    List<JsonElement> elements = adSets.stream().map(adSet -> parser.parse(adSet.getRawValue())).collect(Collectors.toList());
                    LOGGER.info("AdSets: {}", gson.toJson(elements));
                });
            });
        });
    }
}