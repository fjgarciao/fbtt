package com.fjgarciao.fbtt.service;

import com.facebook.ads.sdk.*;
import com.fjgarciao.fbtt.dto.CreateCampaignsQuery;
import com.fjgarciao.fbtt.helper.AdSetDateCalculator;
import com.fjgarciao.fbtt.helper.MarketingApiHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

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

    public void createCalendarCampaign(CreateCampaignsQuery createCampaignsQuery) {
        APIContext apiContext = marketingApiHelper.createContext(accessToken, enableDebug);

        try {
            String campaignName = createCampaignsQuery.getCampaignName();
            Campaign campaign = marketingApiHelper.createCampaign(apiContext, adAccountId, campaignName,
                    "AUCTION", Campaign.EnumObjective.VALUE_BRAND_AWARENESS, Campaign.EnumStatus.VALUE_PAUSED);

            LOGGER.debug("Campaign Created: {}", campaign);

            AdCreative adCreative = marketingApiHelper.createAdCreative(apiContext, adAccountId, "Creative", pageId, "This is the title", "This is the body", "http://www.facebookmarketingdevelopers.com/static/images/resource_1.jpg");

            LOGGER.debug("AdCreative Created: {}", adCreative);

            createCampaignsQuery.parseCountries().entrySet().stream().forEach(entry -> {
                String country = entry.getKey();
                Date date = entry.getValue();
                Targeting targeting = marketingApiHelper.createTargeting(country, createCampaignsQuery.getAgeMin(), createCampaignsQuery.getAgeMax());

                LOGGER.debug("Targeting Created: {}", targeting);

                try {
                    AdSet adSet = marketingApiHelper.createAdSet(apiContext, adAccountId, campaign.getId(),
                            String.format("AdSet %s-%s", campaignName, country), pageId,
                            AdSet.EnumOptimizationGoal.VALUE_BRAND_AWARENESS, AdSet.EnumBillingEvent.VALUE_IMPRESSIONS,
                            createCampaignsQuery.getLifeTimeBudget(), targeting,
                            String.valueOf(adSetDateCalculator.calculate(date, createCampaignsQuery.getStartOffsetR(), createCampaignsQuery.getStartOffsetDays()).getTime()),
                            String.valueOf(adSetDateCalculator.calculate(date, createCampaignsQuery.getEndOffsetR(), createCampaignsQuery.getEndOffsetDays()).getTime()),
                            AdSet.EnumStatus.VALUE_PAUSED);

                    LOGGER.debug("AdSet Created: {}", adSet);

                    Ad ad = marketingApiHelper.createAd(apiContext, adAccountId,
                            String.format("Ad %s-%s", campaignName, country), adSet.getId(), adCreative.getId(), Ad.EnumStatus.VALUE_PAUSED);

                    LOGGER.debug("Ad Created: {}", ad);
                } catch (APIException e) {
                    LOGGER.error("Unable to create adSet", e);
                }
            });
        } catch (APIException e) {
            LOGGER.error("Unable to create calendar campaign", e);
        }
    }

    public Campaign createCampaign() throws APIException {
        APIContext context = new APIContext(accessToken).enableDebug(true);

        Campaign campaign = new AdAccount(adAccountId, context).createCampaign()
                .setName("My Campaign")
                .setBuyingType("AUCTION")
                .setObjective(Campaign.EnumObjective.VALUE_PAGE_LIKES)
                .setStatus(Campaign.EnumStatus.VALUE_PAUSED)
                .execute();
        String campaign_id = campaign.getId();

        AdSet adSet = new AdAccount(adAccountId, context).createAdSet()
                .setName("My AdSet")
                .setOptimizationGoal(AdSet.EnumOptimizationGoal.VALUE_PAGE_LIKES)
                .setBillingEvent(AdSet.EnumBillingEvent.VALUE_IMPRESSIONS)
                .setBidAmount(20L)
                .setPromotedObject("{\"page_id\": " + pageId + "}")
                .setDailyBudget(1000L)
                .setCampaignId(campaign_id)
                .setTargeting(
                        new Targeting()
                                .setFieldGeoLocations(
                                        new TargetingGeoLocation()
                                                .setFieldCountries(Arrays.asList("US"))
                                )
                )
                .setStatus(AdSet.EnumStatus.VALUE_PAUSED)
                .execute();
        String ad_set_id = adSet.getId();

        AdCreative creative = new AdAccount(adAccountId, context).createAdCreative()
                .setName("My Creative")
                .setObjectId(pageId)
                .setTitle("My Page Like Ad")
                .setBody("Like My Page")
                .setImageUrl("http://www.facebookmarketingdevelopers.com/static/images/resource_1.jpg")
                .execute();
        String creative_id = creative.getId();

        Ad ad = new AdAccount(adAccountId, context).createAd()
                .setName("My Ad")
                .setAdsetId(ad_set_id)
                .setCreative(
                        new AdCreative()
                                .setFieldId(creative_id)
                )
                .setStatus(Ad.EnumStatus.VALUE_PAUSED)
                .execute();
        String ad_id = ad.getId();

        APINodeList<AdPreview> adPreviews = new Ad(ad_id, context).getPreviews()
                .setAdFormat(AdPreview.EnumAdFormat.VALUE_DESKTOP_FEED_STANDARD)
                .execute();

        adPreviews.forEach(ap -> LOGGER.debug(ap.getFieldBody()));

        return campaign;
    }
}
