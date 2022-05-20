package com.freemex.open.api.service.account;

import com.freemex.open.api.domain.account.AccountBalanceResponse;
import com.freemex.open.api.service.ApiCallback;

import java.util.List;

public interface AccountAsyncService {

    /**
     * Fetch account balance
     *
     * @param callback the callback that handles the response
     * @return
     */
    void getAccountBalance(ApiCallback<List<AccountBalanceResponse>> callback);
}
