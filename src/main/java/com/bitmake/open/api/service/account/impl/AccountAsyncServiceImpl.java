package com.bitmake.open.api.service.account.impl;

import com.bitmake.open.api.client.ApiServiceGenerator;
import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.domain.account.AccountBalanceResponse;
import com.bitmake.open.api.service.ApiCallback;
import com.bitmake.open.api.service.ApiCallbackAdapter;
import com.bitmake.open.api.service.account.AccountAsyncService;

import java.util.List;

public class AccountAsyncServiceImpl implements AccountAsyncService {

    private final AccountServiceRetrofit accountServiceRetrofit;

    public AccountAsyncServiceImpl(BitmakeApiConfig config) {
        accountServiceRetrofit = ApiServiceGenerator.createService(AccountServiceRetrofit.class, config);
    }

    @Override
    public void getAccountBalance(ApiCallback<List<AccountBalanceResponse>> callback) {
        accountServiceRetrofit.getAccountBalance().enqueue(new ApiCallbackAdapter<>(callback));
    }
}
