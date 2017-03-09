package org.infobip.helper;

import org.infobip.domain.Account;
import org.infobip.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelper {

    @Autowired
    private AccountRepository accountRepository;

    public Account loadAuthenticatedAccount() {
        return accountRepository.findByAccountId(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
