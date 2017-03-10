package org.infobip.service;

import org.infobip.Request.CreateAccountRequest;
import org.infobip.Response.CreateAccountResponse;
import org.infobip.domain.Account;

public interface AccountService {
    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

    Account getAccountByAccountId(String accountId);
}
