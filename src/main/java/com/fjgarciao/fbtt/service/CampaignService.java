package com.fjgarciao.fbtt.service;

import com.facebook.ads.sdk.*;
import com.fjgarciao.fbtt.component.AdSetDateCalculator;
import com.fjgarciao.fbtt.dto.CountrySelectionQuery;
import com.fjgarciao.fbtt.dto.CreateCampaignQuery;
import com.fjgarciao.fbtt.helper.MarketingApiHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CampaignService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignService.class);

    private String accessToken;
    private String adAccountId;
    private String pageId;
    private boolean enableDebug;
    private MarketingApiHelper marketingApiHelper;
    private AdSetDateCalculator adSetDateCalculator;

    @Autowired
    public CampaignService(@Value("${facebook.access_token}") String accessToken,
                           @Value("${facebook.ad_account_id}") String adAccountId,
                           @Value("${facebook.page_id}") String pageId,
                           @Value("${facebook.enable_debug}") boolean enableDebug,
                           MarketingApiHelper marketingApiHelper,
                           AdSetDateCalculator adSetDateCalculator) {
        this.accessToken = accessToken;
        this.adAccountId = adAccountId;
        this.pageId = pageId;
        this.enableDebug = enableDebug;
        this.marketingApiHelper = marketingApiHelper;
        this.adSetDateCalculator = adSetDateCalculator;
    }

    public Optional<String> createCalendarCampaign(CreateCampaignQuery createCampaignQuery,
                                                   CountrySelectionQuery countrySelectionQuery) {
        APIContext apiContext = marketingApiHelper.createContext(accessToken, enableDebug);
        AdAccount account = new AdAccount(adAccountId, apiContext);

        try {
            BatchRequest batchRequest = new BatchRequest(apiContext);

            String campaignName = createCampaignQuery.getCampaignName();
            Campaign campaign = marketingApiHelper.createCampaign(account, campaignName,
                    "AUCTION", Campaign.EnumObjective.VALUE_PAGE_LIKES, Campaign.EnumStatus.VALUE_PAUSED);
            String campaignId = campaign.getId();

            final String adCreativeRequest = "adCreativeRequest";
            final String adCreativeId = String.format("{creative_id:{result=%s:$.id}}", adCreativeRequest);
            marketingApiHelper.createAdCreativeBatch(batchRequest, adCreativeRequest, account,
                    "Creative", pageId, "This is the title", "This is the body",
                    "http://www.facebookmarketingdevelopers.com/static/images/resource_1.jpg");

            countrySelectionQuery.parseCountries().entrySet().stream().forEach(entry -> {
                final String country = entry.getKey();
                final Date date = entry.getValue();
                final String adSetRequest = String.format("adSetRequest%s", country);
                final String adSetId = String.format("{result=%s:$.id}", adSetRequest);
                final String adRequest = String.format("adRequest%s", country);

                final Date startDate = adSetDateCalculator.calculate(date, countrySelectionQuery.getStartOffsetR(), countrySelectionQuery.getStartOffsetDays());
                final Date endDate = adSetDateCalculator.calculate(date, countrySelectionQuery.getEndOffsetR(), countrySelectionQuery.getEndOffsetDays());

                LOGGER.debug("Creating AdSet. country: {}, startDate: {}, endDate: {}", country, startDate, endDate);

                Targeting targeting = marketingApiHelper.createTargeting(country, createCampaignQuery.getAgeMin(), createCampaignQuery.getAgeMax());

                marketingApiHelper.createAdSetBatch(batchRequest, adSetRequest, account,
                        String.format("AdSet %s-%s", campaignName, country), campaignId, pageId,
                        AdSet.EnumOptimizationGoal.VALUE_PAGE_LIKES, AdSet.EnumBillingEvent.VALUE_IMPRESSIONS,
                        createCampaignQuery.getLifeTimeBudget() * 100, targeting,
                        String.valueOf(startDate.getTime() / 1000),
                        String.valueOf(endDate.getTime() / 1000),
                        AdSet.EnumStatus.VALUE_PAUSED);

                marketingApiHelper.createAdBatch(batchRequest, adRequest, account,
                        String.format("Ad %s-%s", campaignName, country), adSetId, adCreativeId,
                        Ad.EnumStatus.VALUE_PAUSED);
            });

            List<APIResponse> responses = batchRequest.execute();
            if (LOGGER.isDebugEnabled()) {
                responses.stream()
                        .filter(Objects::nonNull)
                        .forEach(it -> LOGGER.debug("Response: {}", it.getRawResponse()));
            }

            return Optional.of(campaignId);
        } catch (APIException e) {
            LOGGER.error("Unable to create calendar campaign", e);
            return Optional.empty();
        }
    }

    public Optional<Campaign> getCampaign(String campaignId, String... fields) {
        APIContext apiContext = marketingApiHelper.createContext(accessToken, enableDebug);

        try {
            Campaign campaign = marketingApiHelper.getCampaign(apiContext, campaignId, fields);
            return Optional.of(campaign);
        } catch (APIException e) {
            LOGGER.error("Unable to get campaign with id: {}", campaignId);
            return Optional.empty();
        }
    }

    public Optional<APINodeList<AdSet>> getCampaignAdSets(String campaignId, String... fields) {
        APIContext apiContext = marketingApiHelper.createContext(accessToken, enableDebug);

        try {
            APINodeList<AdSet> adSets = marketingApiHelper.getCampaignAdSets(apiContext, campaignId, fields);
            return Optional.of(adSets);
        } catch (APIException e) {
            LOGGER.error("Unable to get ad sets from campaign {}", campaignId);
            return Optional.empty();
        }
    }

    public Optional<APINodeList<Campaign>> getCampaigns() {
        APIContext apiContext = marketingApiHelper.createContext(accessToken, enableDebug);
        AdAccount account = new AdAccount(adAccountId, apiContext);

        try {
            APINodeList<Campaign> campaigns = marketingApiHelper.getCampaigns(account);
            return Optional.of(campaigns);
        } catch (APIException e) {
            LOGGER.error("Unable to get campaigns", e);
            return Optional.empty();
        }
    }

    public int removeCampaigns() {
        APIContext apiContext = marketingApiHelper.createContext(accessToken, enableDebug);
        AdAccount account = new AdAccount(adAccountId, apiContext);

        try {
            BatchRequest batchRequest = new BatchRequest(apiContext);

            APINodeList<Campaign> campaigns = marketingApiHelper.getCampaigns(account);
            campaigns.stream().forEach(campaign -> {
                campaign.delete().addToBatch(batchRequest);
            });

            return batchRequest.execute().size();
        } catch (APIException e) {
            LOGGER.error("Unable to remove campaigns", e);
        }

        return 0;
    }
}
