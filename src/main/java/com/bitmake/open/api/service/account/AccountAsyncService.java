package com.bitmake.open.api.service.account;

import com.bitmake.open.api.domain.account.AccountBalanceResponse;
import com.bitmake.open.api.service.ApiCallback;

import java.util.List;

public interface AccountAsyncService {

    /**
     * Fetch current account balance
     *
     * @param callback the callback that handles the response
     * @return
     */
    void getAccountBalance(ApiCallback<List<AccountBalanceResponse>> callback);
}
