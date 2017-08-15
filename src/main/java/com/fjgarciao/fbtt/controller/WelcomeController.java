package com.fjgarciao.fbtt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class WelcomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpSession session) {
        LOGGER.debug("index() is executed!");
        session.invalidate();
        return "redirect:/calendar";
    }
}
