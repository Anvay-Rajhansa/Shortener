package org.infobip.service.impl;

import org.infobip.Exception.ShortenerBusinessException;
import org.infobip.Request.RegisterUrlRequest;
import org.infobip.Response.RegisterUrlResponse;
import org.infobip.domain.Account;
import org.infobip.domain.UrlDetails;
import org.infobip.domain.repository.AccountRepository;
import org.infobip.domain.repository.UrlDetailsRepository;
import org.infobip.helper.AuthenticationHelper;
import org.infobip.helper.ShortUrlGenerator;
import org.infobip.service.UrlDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UrlDetailsServiceImpl implements UrlDetailsService {
    @Autowired
    private UrlDetailsRepository urlDetailsRepository;

    @Autowired
    private ShortUrlGenerator shortUrlGenerator;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Override
    public RegisterUrlResponse saveUrlDetails(RegisterUrlRequest registerUrlRequest) {
        Account account = authenticationHelper.loadAuthenticatedAccount();
        String url = registerUrlRequest.getUrl();

        UrlDetails urlDetails = urlDetailsRepository.findByUrlAndAccount(url, account);
        if(urlDetails != null) {
            return new RegisterUrlResponse(shortUrlGenerator.buildShortUrl(urlDetails.getShortUrlKey()));
        }

        String shortUrlKey = shortUrlGenerator.generateShortUrlKey();
        Integer redirectType = registerUrlRequest.getRedirectType() != null ?
                registerUrlRequest.getRedirectType() : HttpStatus.FOUND.value();
        UrlDetails newUrlDetails = new UrlDetails(url, shortUrlKey, redirectType, account);
        urlDetailsRepository.save(newUrlDetails);

        String shortUrl = shortUrlGenerator.buildShortUrl(shortUrlKey);
        return new RegisterUrlResponse(shortUrl);
    }

    @Override
    public UrlDetails getUrlDetailsAndIncreaseRedirectionCount(String shortUrlKey) {
        UrlDetails urlDetails = urlDetailsRepository.findByShortUrlKey(shortUrlKey);
        if (urlDetails == null) {
            throw new ShortenerBusinessException("Invalid short url");
        }

        int currentCount = urlDetails.getRedirectionCount();
        urlDetails.setRedirectionCount(++currentCount);
        urlDetailsRepository.save(urlDetails);
        return urlDetails;
    }

    @Override
    public List<UrlDetails> getDetailsByAccount(Account account) {
        return urlDetailsRepository.findByAccount(account);
    }
}
