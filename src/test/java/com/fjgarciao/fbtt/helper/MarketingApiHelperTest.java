package com.fjgarciao.fbtt.helper;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Campaign;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MarketingApiHelperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketingApiHelperTest.class);

    @Value("${facebook.access_token}")
    private String accessToken;

    @Value("${facebook.ad_account_id}")
    private String adAccountId;

    @Value("${facebook.page_id}")
    private String pageId;

    @Value("${facebook.enable_debug}")
    private boolean enableDebug;

    private MarketingApiHelper uit;

    @Before
    public void setup() {
        uit = new MarketingApiHelper();
    }

    @Test
    public void getCampaigns() throws Exception {
        APIContext apiContext = uit.createContext(accessToken, enableDebug);
        AdAccount account = new AdAccount(adAccountId, apiContext);

        APINodeList<Campaign> campaigns = uit.getCampaigns(account);
        campaigns.stream().forEach(campaign -> LOGGER.debug("Campaign: {}", campaign));
    }

}