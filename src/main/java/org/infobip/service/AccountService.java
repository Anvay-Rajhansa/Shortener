package org.infobip.service;

import org.infobip.request.CreateAccountRequest;
import org.infobip.response.CreateAccountResponse;
import org.infobip.domain.Account;

public interface AccountService {
    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

    Account getAccountByAccountId(String accountId);
}
