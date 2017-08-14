package com.fjgarciao.fbtt.controller;

import com.fjgarciao.fbtt.dto.SelectMarketingDayQuery;
import com.fjgarciao.fbtt.dto.CountrySelectionQuery;
import com.fjgarciao.fbtt.helper.MarketingCalendar;
import com.fjgarciao.fbtt.helper.MarketingDay;
import com.fjgarciao.fbtt.helper.MarketingDayFactory;
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

    @Autowired
    public MarketingCalendarController(MarketingDayFactory marketingDayFactory, MarketingCalendar marketingCalendar) {
        this.marketingDayFactory = marketingDayFactory;
        this.marketingCalendar = marketingCalendar;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initializeForm(@ModelAttribute("selectMarketingDayQuery") SelectMarketingDayQuery selectMarketingDayQuery,
                                 ModelMap model) {
        LOGGER.debug("MarketingCalendarController.initializeForm");

        List<MarketingDay> allMarketingDays = marketingDayFactory.getAllMarketingDays();
        model.addAttribute("marketingDays", allMarketingDays);

        return "selectMarketingDay";
    }

    @RequestMapping(value = "/selectCountries", method = RequestMethod.POST)
    public String selectCountries(@Valid SelectMarketingDayQuery selectMarketingDayQuery,
                                  @ModelAttribute("createCampaignsQuery") CountrySelectionQuery createCampaignsQuery,
                                  BindingResult result,
                                  ModelMap model) {
        LOGGER.debug("MarketingCalendarController.selectCountries. Query: {}", selectMarketingDayQuery);

        MarketingDay marketingDay = marketingDayFactory.getMarketingDayByName(selectMarketingDayQuery.getMarketingDay()).get();
        Map<CountryCode, Date> countriesData = marketingCalendar.getValuesFromMarketingDay(marketingDay, selectMarketingDayQuery.getYear());
        model.addAttribute("countriesData", countriesData);

        return "selectCountries";
    }

    @RequestMapping(value = "/createCampaigns", method = RequestMethod.POST)
    public String createCampaigns(@Valid CountrySelectionQuery countrySelectionQuery, BindingResult result, ModelMap model) {
        LOGGER.debug("MarketingCalendarController.createCampaigns. Query: {}", countrySelectionQuery);

        /*
        MarketingDay marketingDay = marketingDayFactory.getMarketingDayByName(countrySelectionQuery.getMarketingDay()).get();
        Map<CountryCode, Date> calendarData = marketingCalendar.getValuesFromMarketingDay(marketingDay, 2017);
        model.addAttribute("calendarData", calendarData);
        */
        model.addAttribute("countrySelectionQuery", countrySelectionQuery);
        return "showData";
    }
}
