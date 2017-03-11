package org.infobip.request;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.infobip.validator.ValidateRedirectType;

import javax.validation.constraints.NotNull;

public class RegisterUrlRequest {
    @NotNull
    @NotBlank
    @URL
    String url;

    @ValidateRedirectType
    Integer redirectType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }
}
