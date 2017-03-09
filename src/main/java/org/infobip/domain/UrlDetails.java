package org.infobip.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"url", "account_id"})})
public class UrlDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    private String shortUrlKey;
    private Integer redirectionType;
    private Integer redirectionCount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public UrlDetails() {
    }

    public UrlDetails(String url, String shortUrlKey, Integer redirectionType, Account account) {
        this.url = url;
        this.shortUrlKey = shortUrlKey;
        this.redirectionType = redirectionType;
        this.account = account;
        this.redirectionCount = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrlKey() {
        return shortUrlKey;
    }

    public void setShortUrlKey(String shortUrlKey) {
        this.shortUrlKey = shortUrlKey;
    }

    public Integer getRedirectionType() {
        return redirectionType;
    }

    public void setRedirectionType(Integer redirectionType) {
        this.redirectionType = redirectionType;
    }

    public Integer getRedirectionCount() {
        return redirectionCount;
    }

    public void setRedirectionCount(Integer redirectionCount) {
        this.redirectionCount = redirectionCount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UrlDetails that = (UrlDetails) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(url, that.url)
                .append(shortUrlKey, that.shortUrlKey)
                .append(redirectionType, that.redirectionType)
                .append(redirectionCount, that.redirectionCount)
                .append(account, that.account)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(url)
                .append(shortUrlKey)
                .append(redirectionType)
                .append(redirectionCount)
                .append(account)
                .toHashCode();
    }
}
