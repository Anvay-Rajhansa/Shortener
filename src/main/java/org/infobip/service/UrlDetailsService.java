package org.infobip.service;

import org.infobip.Request.RegisterUrlRequest;
import org.infobip.Response.RegisterUrlResponse;
import org.infobip.domain.Account;
import org.infobip.domain.UrlDetails;

import java.util.List;

public interface UrlDetailsService {
    RegisterUrlResponse saveUrlDetails(RegisterUrlRequest registerUrlRequest);

    UrlDetails getUrlDetailsAndIncreaseRedirectionCount(String shortUrlKey);

    List<UrlDetails> getDetailsByAccount(Account account);
}
