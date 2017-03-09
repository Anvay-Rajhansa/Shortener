package org.infobip.service;

import org.infobip.Request.CreateAccountRequest;
import org.infobip.Response.CreateAccountResponse;
import org.infobip.domain.Account;

import java.util.List;

public interface AccountService {
    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

    Account getAccountByAccountId(String accountId);
}
