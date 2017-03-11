package org.infobip.service.impl;

import org.infobip.exception.ShortenerBusinessException;
import org.infobip.request.RegisterUrlRequest;
import org.infobip.response.RegisterUrlResponse;
import org.infobip.domain.Account;
import org.infobip.domain.UrlDetails;
import org.infobip.helper.AuthenticationHelper;
import org.infobip.helper.ShortUrlGenerator;
import org.infobip.repository.UrlDetailsRepository;
import org.infobip.service.UrlDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
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
        if (urlDetails != null) {
            return new RegisterUrlResponse(getShortUrl(urlDetails.getShortUrlKey()));
        }

        String shortUrlKey = shortUrlGenerator.generateShortUrlKey();
        Integer redirectType = getRedirectType(registerUrlRequest);

        UrlDetails newUrlDetails = new UrlDetails(url, shortUrlKey, redirectType, account);
        urlDetailsRepository.save(newUrlDetails);

        return new RegisterUrlResponse(getShortUrl(shortUrlKey));
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

    private String getShortUrl(String shortUrlKey) {
        return shortUrlGenerator.buildShortUrl(shortUrlKey);
    }

    private Integer getRedirectType(RegisterUrlRequest registerUrlRequest) {
        return registerUrlRequest.getRedirectType() != null ?
                registerUrlRequest.getRedirectType() : HttpServletResponse.SC_MOVED_TEMPORARILY;
    }
}
