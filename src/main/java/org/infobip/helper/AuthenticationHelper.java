package org.infobip.helper;

import org.infobip.exception.ShortenerBusinessException;
import org.infobip.domain.Account;
import org.infobip.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelper {

    @Autowired
    private AccountRepository accountRepository;

    public Account loadAuthenticatedAccount() {
        Account account = accountRepository.findByAccountId(
                SecurityContextHolder.getContext().getAuthentication().getName());
        if (account == null) {
            throw new ShortenerBusinessException("Invalid logged-in account name.");
        }
        return account;
    }
}
