package com.fjgarciao.fbtt.controller;

import com.fjgarciao.fbtt.dto.CountrySelectionQuery;
import com.fjgarciao.fbtt.dto.CreateCampaignsQuery;
import com.fjgarciao.fbtt.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignController.class);

    @RequestMapping(method = RequestMethod.POST)
    public String createCampaigns(@Valid CreateCampaignsQuery createCampaignsQuery, BindingResult result, ModelMap model) {
        LOGGER.debug("CampaignController.createCampaigns. Query: {}", createCampaignsQuery);

        /*
        MarketingDay marketingDay = marketingDayFactory.getMarketingDayByName(createCampaignsQuery.getMarketingDay()).get();
        Map<CountryCode, Date> calendarData = marketingCalendar.getValuesFromMarketingDay(marketingDay, 2017);
        model.addAttribute("calendarData", calendarData);
        */
        model.addAttribute("createCampaignsQuery", createCampaignsQuery);
        return "showData";
    }
}
