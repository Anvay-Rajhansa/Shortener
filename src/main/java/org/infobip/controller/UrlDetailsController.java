package org.infobip.controller;

import org.infobip.Request.RegisterUrlRequest;
import org.infobip.Response.RegisterUrlResponse;
import org.infobip.service.UrlDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlDetailsController {
    @Autowired
    UrlDetailsService urlDetailsService;

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    RegisterUrlResponse registerUrl(@Validated @RequestBody RegisterUrlRequest registerUrlRequest) {
        return urlDetailsService.saveUrlDetails(registerUrlRequest);
    }
}
