package com.fjgarciao.fbtt.service;

import com.facebook.ads.sdk.*;
import com.fjgarciao.fbtt.dto.CreateCampaignsQuery;
import com.fjgarciao.fbtt.helper.AdSetDateCalculator;
import com.fjgarciao.fbtt.helper.MarketingApiHelper;
import com.fjgarciao.fbtt.util.CalendarUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        AdAccount account = new AdAccount(adAccountId, apiContext);
        final String campaignRequest = "campaignRequest";
        final String adCreativeRequest = "adCreativeRequest";

        try {
            BatchRequest batchRequest = new BatchRequest(apiContext);

            String campaignName = createCampaignsQuery.getCampaignName();
            marketingApiHelper.createCampaignBatch(batchRequest, campaignRequest, account, campaignName,
                    "AUCTION", Campaign.EnumObjective.VALUE_PAGE_LIKES, Campaign.EnumStatus.VALUE_PAUSED);

            marketingApiHelper.createAdCreativeBatch(batchRequest, adCreativeRequest, account,
                    "Creative", pageId, "This is the title", "This is the body",
                    "http://www.facebookmarketingdevelopers.com/static/images/resource_1.jpg");

            createCampaignsQuery.parseCountries().entrySet().stream().forEach(entry -> {
                final String country = entry.getKey();
                final Date date = CalendarUtils.prepareCalendar(2017, Calendar.OCTOBER, 15).getTime(); //entry.getValue();
                final String adSetRequest = String.format("adSetRequest%s", country);
                final String adRequest = String.format("adRequest%s", country);
                final Date startDate = adSetDateCalculator.calculate(date, createCampaignsQuery.getStartOffsetR(), createCampaignsQuery.getStartOffsetDays());
                final Date endDate = adSetDateCalculator.calculate(date, createCampaignsQuery.getEndOffsetR(), createCampaignsQuery.getEndOffsetDays());

                LOGGER.debug("Creating AdSet. country: {}, startDate: {}, endDate: {}", country, startDate, endDate);

                Targeting targeting = marketingApiHelper.createTargeting(country, createCampaignsQuery.getAgeMin(), createCampaignsQuery.getAgeMax());

                marketingApiHelper.createAdSetBatch(batchRequest, adSetRequest, account,
                        String.format("AdSet %s-%s", campaignName, country), campaignRequest, pageId,
                        AdSet.EnumOptimizationGoal.VALUE_PAGE_LIKES, AdSet.EnumBillingEvent.VALUE_IMPRESSIONS,
                        createCampaignsQuery.getLifeTimeBudget(), targeting,
                        String.valueOf(startDate.getTime() / 1000),
                        String.valueOf(endDate.getTime() / 1000),
                        AdSet.EnumStatus.VALUE_PAUSED);

                marketingApiHelper.createAdBatch(batchRequest, adRequest, account,
                        String.format("Ad %s-%s", campaignName, country), adSetRequest, adCreativeRequest,
                        Ad.EnumStatus.VALUE_PAUSED);
            });

            List<APIResponse> responses = batchRequest.execute();
            if (LOGGER.isDebugEnabled()) {
                responses.stream().filter(it -> it != null).forEach(it -> LOGGER.debug("Response: {}", it.getRawResponse()));
            }
        } catch (Exception e) {
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
