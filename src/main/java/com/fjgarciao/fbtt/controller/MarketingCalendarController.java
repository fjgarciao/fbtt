package com.fjgarciao.fbtt.controller;

import com.fjgarciao.fbtt.helper.MarketingDay;
import com.fjgarciao.fbtt.helper.MarketingDayFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/calendar")
public class MarketingCalendarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketingCalendarController.class);

    private MarketingDayFactory marketingDayFactory;

    @Autowired
    public MarketingCalendarController(MarketingDayFactory marketingDayFactory) {
        this.marketingDayFactory = marketingDayFactory;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initializeForm(ModelMap model) {
        LOGGER.debug("MarketingCalendarController.initializeForm");

        List<MarketingDay> allMarketingDays = marketingDayFactory.getAllMarketingDays();
        model.addAttribute("marketingDays", allMarketingDays);

        return "marketing_calendar";
    }
}
