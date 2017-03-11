package org.infobip.controller;

import org.infobip.exception.ShortenerBusinessException;
import org.infobip.request.CreateAccountRequest;
import org.infobip.response.CreateAccountResponse;
import org.infobip.domain.Account;
import org.infobip.domain.UrlDetails;
import org.infobip.service.AccountService;
import org.infobip.service.UrlDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UrlDetailsService urlDetailsService;

    @RequestMapping(value = "/account", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    CreateAccountResponse createAccount(
            @Validated @RequestBody CreateAccountRequest createAccountRequest, HttpServletResponse response) {
        CreateAccountResponse createAccountResponse = accountService.createAccount(createAccountRequest);
        if (createAccountResponse.isStatus()) {
            response.setStatus(HttpStatus.CREATED.value());
        }
        return createAccountResponse;
    }

    @RequestMapping(value = "/statistic/{AccountId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Map<String, Integer> getAccountStatistic(@PathVariable("AccountId") String accountId) {
        Account account = accountService.getAccountByAccountId(accountId);

        if (account != null) {
            List<UrlDetails> urlDetailsList = urlDetailsService.getDetailsByAccount(account);
            return urlDetailsList.stream().collect(Collectors.toMap(UrlDetails::getUrl,
                    UrlDetails::getRedirectionCount));
        }

        throw new ShortenerBusinessException("Invalid given accountId.");
    }
}