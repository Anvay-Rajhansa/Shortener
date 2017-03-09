package org.infobip.domain.repository;


import org.infobip.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByAccountId(String accountId);

    List<Account> findAll();
}
