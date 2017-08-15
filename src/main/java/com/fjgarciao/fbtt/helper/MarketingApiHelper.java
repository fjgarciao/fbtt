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

    public Campaign createCampaign(AdAccount account, String name,
                                   String buyingType, Campaign.EnumObjective objective,
                                   Campaign.EnumStatus status) throws APIException {
        Campaign campaign = account.createCampaign()
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

    public AdSet createAdSet(AdAccount account, String campaignId, String name,
                             String pageId, AdSet.EnumOptimizationGoal optimizationGoal,
                             AdSet.EnumBillingEvent billingEvent, long lifeTimeBudget,
                             Targeting targeting, String startTime, String endTime,
                             AdSet.EnumStatus status) throws APIException {
        AdSet adSet = account.createAdSet()
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

    public AdCreative createAdCreative(AdAccount account, String name,
                                       String objectId, String title, String body, String imageUrl) throws APIException {
        AdCreative adCreative = account.createAdCreative()
                .setName(name)
                .setObjectId(objectId)
                .setTitle(title)
                .setBody(body)
                .setImageUrl(imageUrl)
                .execute();

        return adCreative;
    }

    public Ad createAd(AdAccount account, String name, String adSetId,
                       String creativeId, Ad.EnumStatus status) throws APIException {
        Ad ad = account.createAd()
                .setName(name)
                .setAdsetId(adSetId)
                .setCreative(new AdCreative().setFieldId(creativeId))
                .setStatus(status)
                .execute();

        return ad;
    }

    public void createCampaignBatch(BatchRequest batchRequest, String batchName, AdAccount account, String name,
                                    String buyingType, Campaign.EnumObjective objective,
                                    Campaign.EnumStatus status) {
        account.createCampaign()
                .setName(name)
                .setBuyingType(buyingType)
                .setObjective(objective)
                .setStatus(status)
                .addToBatch(batchRequest, batchName);
    }

    public void createAdSetBatch(BatchRequest batchRequest, String batchName,
                                 AdAccount account, String name, String campaignBatchName,
                                 String pageId, AdSet.EnumOptimizationGoal optimizationGoal,
                                 AdSet.EnumBillingEvent billingEvent, long lifeTimeBudget,
                                 Targeting targeting, String startTime, String endTime,
                                 AdSet.EnumStatus status) {
        account.createAdSet()
                .setName(name)
                .setCampaignId(String.format("{result=%s:$.id}", campaignBatchName))
                .setPromotedObject("{\"page_id\": " + pageId + "}")
                .setOptimizationGoal(optimizationGoal)
                .setBillingEvent(billingEvent)
                .setIsAutobid(true)
                .setLifetimeBudget(lifeTimeBudget)
                .setTargeting(targeting)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setStatus(status)
                .addToBatch(batchRequest, batchName);
    }

    public void createAdCreativeBatch(BatchRequest batchRequest, String batchName,
                                      AdAccount account, String name,
                                      String objectId, String title, String body, String imageUrl) {
        account.createAdCreative()
                .setName(name)
                .setObjectId(objectId)
                .setTitle(title)
                .setBody(body)
                .setImageUrl(imageUrl)
                .addToBatch(batchRequest, batchName);
    }

    public void createAdBatch(BatchRequest batchRequest, String batchName,
                              AdAccount account, String name, String adSetId,
                              String creativeId, Ad.EnumStatus status) {
        account.createAd()
                .setName(name)
                .setAdsetId(String.format("{result=%s:$.id}", adSetId))
                .setCreative(String.format("{creative_id:{result=%s:$.id}}", creativeId))
                .setStatus(status)
                .addToBatch(batchRequest, batchName);
    }
}
