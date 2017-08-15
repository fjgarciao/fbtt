package com.fjgarciao.fbtt.controller;

import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdCreative;
import com.facebook.ads.sdk.Campaign;
import com.fjgarciao.fbtt.service.CampaignService;
import com.fjgarciao.fbtt.service.CreativeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
@RequestMapping("/creatives")
public class AdCreativeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdCreativeController.class);

    private CreativeService creativeService;

    @Autowired
    public AdCreativeController(CreativeService creativeService) {
        this.creativeService = creativeService;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody int remove() {
        LOGGER.debug("AdCreativeController.remove");

        return creativeService.removeCreatives();
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String show() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();

        Optional<APINodeList<AdCreative>> creativesOptional = creativeService.getCreatives();
        if (creativesOptional.isPresent()) {
            return gson.toJson(parser.parse(creativesOptional.get().getRawResponse()));
        } else {
            return "{}";
        }
    }
}
