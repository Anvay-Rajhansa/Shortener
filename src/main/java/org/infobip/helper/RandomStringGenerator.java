package org.infobip.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomStringGenerator {
    public String generateAlphanumericStringWithLength(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
