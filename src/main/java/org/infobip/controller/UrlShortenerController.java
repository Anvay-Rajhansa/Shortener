package org.infobip.controller;

import org.infobip.domain.UrlDetails;
import org.infobip.service.UrlDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UrlShortenerController {
    @Autowired
    UrlDetailsService urlDetailsService;

    @RequestMapping(value = "/{shortUrlKey}", method = RequestMethod.GET)
    public void registerUrl(@PathVariable("shortUrlKey") String shortUrlKey, HttpServletResponse httpServletResponse) {
        UrlDetails urlDetails = urlDetailsService.getUrlDetailsAndIncreaseRedirectionCount(shortUrlKey);
        httpServletResponse.setHeader("Location", urlDetails.getUrl());
        httpServletResponse.setStatus(urlDetails.getRedirectionType());
    }
}
