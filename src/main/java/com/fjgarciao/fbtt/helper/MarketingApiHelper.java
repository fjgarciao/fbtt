package com.fjgarciao.fbtt.helper;

import com.facebook.ads.sdk.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MarketingApiHelper {

    public APIContext createContext(String accessToken, boolean enableDebug) {
        APIContext context = new APIContext(accessToken).enableDebug(enableDebug);
        return context;
    }

    public Campaign createCampaign(APIContext context, String adAccountId, String name,
                                   String buyingType, Campaign.EnumObjective objective,
                                   Campaign.EnumStatus status) throws APIException {
        Campaign campaign = new AdAccount(adAccountId, context).createCampaign()
                .setName(name)
                .setBuyingType(buyingType)
                .setObjective(objective)
                .setStatus(status)
                .execute();

        return campaign;
    }

    public Targeting createTargeting(String country, long ageMin, long ageMax) {
        return new Targeting()
                .setFieldGeoLocations(new TargetingGeoLocation().setFieldCountries(Arrays.asList(country)))
                .setFieldAgeMin(ageMin)
                .setFieldAgeMax(ageMax);
    }

    public AdSet createAdSet(APIContext context, String adAccountId, String campaignId, String name,
                             String pageId, AdSet.EnumOptimizationGoal optimizationGoal,
                             AdSet.EnumBillingEvent billingEvent, long lifeTimeBudget,
                             Targeting targeting, String startTime, String endTime,
                             AdSet.EnumStatus status) throws APIException {
        AdSet adSet = new AdAccount(adAccountId, context).createAdSet()
                .setCampaignId(campaignId)
                .setName(name)
                .setPromotedObject("{\"page_id\": " + pageId + "}")
                .setOptimizationGoal(optimizationGoal)
                .setBillingEvent(billingEvent)
                .setIsAutobid(true)
                .setLifetimeBudget(lifeTimeBudget)
                .setTargeting(targeting)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setStatus(status)
                .execute();

        return adSet;
    }

    public AdCreative createAdCreative(APIContext context, String adAccountId, String name,
                                       String objectId, String title, String body, String imageUrl) throws APIException {
        AdCreative adCreative = new AdAccount(adAccountId, context).createAdCreative()
                .setName(name)
                .setObjectId(objectId)
                .setTitle(title)
                .setBody(body)
                .setImageUrl(imageUrl)
                .execute();

        return adCreative;
    }

    public Ad createAd(APIContext context, String adAccountId, String name, String adSetId,
                       String creativeId, Ad.EnumStatus status)  throws APIException {
        Ad ad = new AdAccount(adAccountId, context).createAd()
                .setName(name)
                .setAdsetId(adSetId)
                .setCreative(new AdCreative().setFieldId(creativeId))
                .setStatus(status)
                .execute();

        return ad;
    }
}
