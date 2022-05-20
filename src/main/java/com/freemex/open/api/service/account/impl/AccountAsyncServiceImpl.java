package com.freemex.open.api.service.account.impl;

import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.domain.account.AccountBalanceResponse;
import com.freemex.open.api.service.ApiCallback;
import com.freemex.open.api.service.ApiCallbackAdapter;
import com.freemex.open.api.service.account.AccountAsyncService;

import java.util.List;

import static com.freemex.open.api.client.ApiServiceGenerator.createService;

public class AccountAsyncServiceImpl implements AccountAsyncService {

    private final AccountServiceRetrofit accountServiceRetrofit;

    public AccountAsyncServiceImpl(FreemexApiConfig config) {
        accountServiceRetrofit = createService(AccountServiceRetrofit.class, config);
    }

    @Override
    public void getAccountBalance(ApiCallback<List<AccountBalanceResponse>> callback) {
        accountServiceRetrofit.getAccountBalance().enqueue(new ApiCallbackAdapter<>(callback));
    }
}
