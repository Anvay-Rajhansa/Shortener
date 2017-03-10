package org.infobip.config;

import org.infobip.domain.Account;
import org.infobip.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AccountService accountService;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        String accountId = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        Account account = accountService.getAccountByAccountId(accountId);

        if (account == null) {
            throw new BadCredentialsException("Invalid username.");
        }

        if (!password.equals(account.getPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new UsernamePasswordAuthenticationToken(accountId, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
