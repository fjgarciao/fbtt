package com.fjgarciao.fbtt.service;

import com.facebook.ads.sdk.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CampaignService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignService.class);

    private String accessToken;
    private String adAccountId;
    private String appSecret;
    private String pageId;

    @Autowired
    public CampaignService(@Value("${facebook.access_token}") String accessToken,
                           @Value("${facebook.ad_account_id}") String adAccountId,
                           @Value("${facebook.app_secret}") String appSecret,
                           @Value("${facebook.page_id}") String pageId) {
        this.accessToken = accessToken;
        this.adAccountId = adAccountId;
        this.appSecret = appSecret;
        this.pageId = pageId;
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
