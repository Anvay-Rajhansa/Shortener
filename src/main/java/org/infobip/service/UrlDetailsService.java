package org.infobip.service;

import org.infobip.request.RegisterUrlRequest;
import org.infobip.response.RegisterUrlResponse;
import org.infobip.domain.Account;
import org.infobip.domain.UrlDetails;

import java.util.List;

public interface UrlDetailsService {
    RegisterUrlResponse saveUrlDetails(RegisterUrlRequest registerUrlRequest);

    UrlDetails getUrlDetailsAndIncreaseRedirectionCount(String shortUrlKey);

    List<UrlDetails> getDetailsByAccount(Account account);
}
