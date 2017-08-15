package com.fjgarciao.fbtt.controller;

import com.fjgarciao.fbtt.dto.CreateCampaignQuery;
import com.fjgarciao.fbtt.dto.CountrySelectionQuery;
import com.fjgarciao.fbtt.component.MarketingCalendar;
import com.fjgarciao.fbtt.component.MarketingDay;
import com.fjgarciao.fbtt.component.MarketingDayFactory;
import com.fjgarciao.fbtt.service.CampaignService;
import com.neovisionaries.i18n.CountryCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/calendar")
public class MarketingCalendarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketingCalendarController.class);

    private MarketingDayFactory marketingDayFactory;
    private MarketingCalendar marketingCalendar;
    private CampaignService campaignService;

    @Autowired
    public MarketingCalendarController(MarketingDayFactory marketingDayFactory, MarketingCalendar marketingCalendar,
                                       CampaignService campaignService) {
        this.marketingDayFactory = marketingDayFactory;
        this.marketingCalendar = marketingCalendar;
        this.campaignService = campaignService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initializeForm(@ModelAttribute("createCampaignQuery") CreateCampaignQuery createCampaignQuery,
                                 ModelMap model) {
        LOGGER.debug("MarketingCalendarController.initializeForm");

        List<MarketingDay> allMarketingDays = marketingDayFactory.getAllMarketingDays();
        model.addAttribute("marketingDays", allMarketingDays);

        return "createCampaign";
    }

    @RequestMapping(value = "/selectCountries", method = RequestMethod.POST)
    public String selectCountries(@Valid CreateCampaignQuery createCampaignQuery,
                                  @ModelAttribute("countrySelectionQuery") CountrySelectionQuery countrySelectionQuery,
                                  BindingResult result,
                                  ModelMap model) {
        LOGGER.debug("MarketingCalendarController.selectCountries. Query: {}", createCampaignQuery);

        MarketingDay marketingDay = marketingDayFactory.getMarketingDayByName(createCampaignQuery.getMarketingDay()).get();
        Map<CountryCode, Date> countriesData = marketingCalendar.getValuesFromMarketingDay(marketingDay, createCampaignQuery.getYear());
        model.addAttribute("countriesData", countriesData);

        return "selectCountries";
    }

    @RequestMapping(value = "/createCampaign", method = RequestMethod.POST)
    public String createCampaign(@Valid CountrySelectionQuery countrySelectionQuery,
                                  @ModelAttribute("createCampaignQuery") CreateCampaignQuery createCampaignQuery,
                                  BindingResult result, ModelMap model) {
        LOGGER.debug("MarketingCalendarController.createCampaign. Query: {}", createCampaignQuery);
        LOGGER.debug("MarketingCalendarController.createCampaign. Query: {}", countrySelectionQuery);

        //campaignService.createCalendarCampaign()
        /*
        MarketingDay marketingDay = marketingDayFactory.getMarketingDayByName(countrySelectionQuery.getMarketingDay()).get();
        Map<CountryCode, Date> calendarData = marketingCalendar.getValuesFromMarketingDay(marketingDay, 2017);
        model.addAttribute("calendarData", calendarData);
        */

        //model.addAttribute("countrySelectionQuery", countrySelectionQuery);
        return "showData";
    }
}
