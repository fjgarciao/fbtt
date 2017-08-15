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
public class CreativeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreativeService.class);

    private String accessToken;
    private String adAccountId;
    private boolean enableDebug;
    private MarketingApiHelper marketingApiHelper;

    @Autowired
    public CreativeService(@Value("${facebook.access_token}") String accessToken,
                           @Value("${facebook.ad_account_id}") String adAccountId,
                           @Value("${facebook.enable_debug}") boolean enableDebug,
                           MarketingApiHelper marketingApiHelper) {
        this.accessToken = accessToken;
        this.adAccountId = adAccountId;
        this.enableDebug = enableDebug;
        this.marketingApiHelper = marketingApiHelper;
    }

    public Optional<AdCreative> getCreative(String creativeId, String... fields) {
        APIContext apiContext = marketingApiHelper.createContext(accessToken, enableDebug);

        try {
            AdCreative creative = marketingApiHelper.getCreative(apiContext, creativeId, fields);
            return Optional.of(creative);
        } catch (APIException e) {
            LOGGER.error("Unable to get creative with id: {}", creativeId);
            return Optional.empty();
        }
    }

    public Optional<APINodeList<AdCreative>> getCreatives() {
        APIContext apiContext = marketingApiHelper.createContext(accessToken, enableDebug);
        AdAccount account = new AdAccount(adAccountId, apiContext);

        try {
            APINodeList<AdCreative> creatives = marketingApiHelper.getCreatives(account);
            return Optional.of(creatives);
        } catch (APIException e) {
            LOGGER.error("Unable to get creatives", e);
            return Optional.empty();
        }
    }

    public int removeCreatives() {
        APIContext apiContext = marketingApiHelper.createContext(accessToken, enableDebug);
        AdAccount account = new AdAccount(adAccountId, apiContext);

        try {
            BatchRequest batchRequest = new BatchRequest(apiContext);

            APINodeList<AdCreative> creatives = marketingApiHelper.getCreatives(account);
            creatives.stream().forEach(adCreative -> {
                adCreative.delete().addToBatch(batchRequest);
            });

            return batchRequest.execute().size();
        } catch (APIException e) {
            LOGGER.error("Unable to remove creatives", e);
        }

        return 0;
    }
}
