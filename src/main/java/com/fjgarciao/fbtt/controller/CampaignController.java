package com.fjgarciao.fbtt.controller;

import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.Campaign;
import com.fjgarciao.fbtt.service.CampaignService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignController.class);

    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody int remove() {
        LOGGER.debug("CampaignController.remove");

        return campaignService.removeCampaigns();
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String show() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();

        Optional<APINodeList<Campaign>> campaignsOptional = campaignService.getCampaigns();
        if (campaignsOptional.isPresent()) {
            return gson.toJson(parser.parse(campaignsOptional.get().getRawResponse()));
        } else {
            return "{}";
        }
    }
}
