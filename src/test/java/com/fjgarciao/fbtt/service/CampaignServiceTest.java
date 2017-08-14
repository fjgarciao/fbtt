package com.fjgarciao.fbtt.service;

import com.facebook.ads.sdk.APIException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CampaignServiceTest {

    @Autowired
    private CampaignService uit;

    @Test
    public void testCreateCampaign() throws APIException {
        uit.createCampaign();
    }
}