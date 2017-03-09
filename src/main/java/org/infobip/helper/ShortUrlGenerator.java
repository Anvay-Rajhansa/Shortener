package org.infobip.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlGenerator {
    private final static int SHORTEN_URL_KEY_LENGTH = 6;

    @Autowired
    private RandomStringGenerator randomStringGenerator;

    @Value("${domain.name}")
    private String domainName;

    @Value("${server.port}")
    private int serverPort;

    public String generateShortUrlKey() {
        return randomStringGenerator.generateAlphanumericStringWithLength(SHORTEN_URL_KEY_LENGTH);
    }

    public String buildShortUrl(String urlKey) {
        return String.format("http://%s:%d/%s", domainName, serverPort, urlKey).toString();
    }
}
