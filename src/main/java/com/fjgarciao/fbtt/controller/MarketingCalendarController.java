package com.fjgarciao.fbtt.controller;

import com.fjgarciao.fbtt.component.MarketingCalendar;
import com.fjgarciao.fbtt.component.MarketingDay;
import com.fjgarciao.fbtt.component.MarketingDayFactory;
import com.fjgarciao.fbtt.dto.CountrySelectionQuery;
import com.fjgarciao.fbtt.dto.CreateCampaignQuery;
import com.fjgarciao.fbtt.service.CampaignService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/calendar")
@SessionAttributes("createCampaignQuery")
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

    @ModelAttribute("createCampaignQuery")
    public CreateCampaignQuery createCampaignQuery() {
        return new CreateCampaignQuery();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initializeForm(ModelMap model) {
        LOGGER.debug("MarketingCalendarController.initializeForm");

        List<MarketingDay> allMarketingDays = marketingDayFactory.getAllMarketingDays();
        model.addAttribute("marketingDays", allMarketingDays);

        return "createCampaign";
    }

    @RequestMapping(value = "/selectCountries", method = RequestMethod.POST)
    public String selectCountries(@Valid CreateCampaignQuery createCampaignQuery,
                                  @ModelAttribute("countrySelectionQuery") CountrySelectionQuery countrySelectionQuery,
                                  ModelMap model) {
        LOGGER.debug("MarketingCalendarController.selectCountries. Query: {}", createCampaignQuery);

        MarketingDay marketingDay = marketingDayFactory.getMarketingDayByName(createCampaignQuery.getMarketingDay()).get();
        Map<CountryCode, Date> countriesData = marketingCalendar.getValuesFromMarketingDay(marketingDay, createCampaignQuery.getYear(), false);
        model.addAttribute("countriesData", countriesData);

        return "selectCountries";
    }

    @RequestMapping(value = "/createCampaign", method = RequestMethod.POST)
    public String createCampaign(@Valid CountrySelectionQuery countrySelectionQuery,
                                 @ModelAttribute("createCampaignQuery") CreateCampaignQuery createCampaignQuery,
                                 BindingResult result, ModelMap model) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        LOGGER.debug("MarketingCalendarController.createCampaign. Query: {}", countrySelectionQuery);

        campaignService.createCalendarCampaign(createCampaignQuery, countrySelectionQuery).ifPresent(campaignId -> {
            final String[] campaignFields = {"id", "name", "configured_status", "created_time", "objective", "start_time", "stop_time"};
            campaignService.getCampaign(campaignId, campaignFields).ifPresent(campaign -> {
                String campaignJson = gson.toJson(parser.parse(campaign.getRawValue()));
                LOGGER.info("Generated Campaign: {}", campaignJson);
                model.addAttribute("campaignData", campaignJson);

                final String[] adSetFields = {"id", "name", "optimization_goal", "promoted_object", "billing_event", "campaign_id", "configured_status", "created_time", "start_time", "end_time", "lifetime_budget", "targeting"};
                campaignService.getCampaignAdSets(campaign.getId(), adSetFields).ifPresent(list -> {
                    List<JsonElement> adSets = list.stream().map(adSet -> parser.parse(adSet.getRawValue())).collect(Collectors.toList());
                    String adSetsJson = gson.toJson(adSets);
                    LOGGER.info("Generated Ad Sets: {}", adSetsJson);
                    model.addAttribute("adSets", adSetsJson);
                });
            });
        });

        return "showResults";
    }
}
