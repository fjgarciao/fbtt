package com.fjgarciao.fbtt.helper;

import com.facebook.ads.sdk.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
                                 AdAccount account, String name, String campaignId,
                                 String pageId, AdSet.EnumOptimizationGoal optimizationGoal,
                                 AdSet.EnumBillingEvent billingEvent, long lifeTimeBudget,
                                 Targeting targeting, String startTime, String endTime,
                                 AdSet.EnumStatus status) {
        account.createAdSet()
                .setName(name)
                .setCampaignId(campaignId)
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
                .setAdsetId(adSetId)
                .setCreative(creativeId)
                .setStatus(status)
                .addToBatch(batchRequest, batchName);
    }

    public Campaign getCampaign(APIContext apiContext, String campaignId, String... fields) throws APIException {
        Campaign.APIRequestGet request = new Campaign(campaignId, apiContext).get();
        if (StringUtils.isEmpty(fields)) {
            request.requestAllFields();
        } else {
            request.requestFields(Arrays.asList(fields));
        }
        return request.execute();
    }

    public APINodeList<Campaign> getCampaigns(AdAccount account, String... fields) throws APIException {
        AdAccount.APIRequestGetCampaigns request = account.getCampaigns();
        if (StringUtils.isEmpty(fields)) {
            request.requestAllFields();
        } else {
            request.requestFields(Arrays.asList(fields));
        }
        return request.execute();
    }

    public APINodeList<AdSet> getCampaignAdSets(APIContext apiContext, String campaignId, String... fields) throws APIException {
        Campaign.APIRequestGetAdSets request = new Campaign(campaignId, apiContext).getAdSets();
        if (StringUtils.isEmpty(fields)) {
            request.requestAllFields();
        } else {
            request.requestFields(Arrays.asList(fields));
        }
        return request.execute();
    }

    public APINodeList<AdCreative> getCreatives(AdAccount account, String... fields) throws APIException {
        AdAccount.APIRequestGetAdCreatives request = account.getAdCreatives();
        if (StringUtils.isEmpty(fields)) {
            request.requestAllFields();
        } else {
            request.requestFields(Arrays.asList(fields));
        }
        return request.execute();
    }

    public AdCreative getCreative(APIContext apiContext, String creativeId, String[] fields) throws APIException {
        AdCreative.APIRequestGet request = new AdCreative(creativeId, apiContext).get();
        if (StringUtils.isEmpty(fields)) {
            request.requestAllFields();
        } else {
            request.requestFields(Arrays.asList(fields));
        }
        return request.execute();
    }
}
