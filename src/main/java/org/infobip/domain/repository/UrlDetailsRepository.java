package org.infobip.domain.repository;

import org.infobip.domain.Account;
import org.infobip.domain.UrlDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UrlDetailsRepository extends CrudRepository<UrlDetails, Long> {
    UrlDetails findByShortUrlKey(String shortUrlKey);

    List<UrlDetails> findByAccount(Account account);

    UrlDetails findByUrlAndAccount(String url, Account account);
}
