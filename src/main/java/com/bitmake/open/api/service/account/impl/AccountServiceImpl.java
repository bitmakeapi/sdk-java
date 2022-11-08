package com.bitmake.open.api.service.account.impl;

import com.bitmake.open.api.domain.account.*;
import com.bitmake.open.api.service.account.AccountService;
import com.bitmake.open.api.config.BitmakeApiConfig;

import java.util.List;

import static com.bitmake.open.api.client.ApiServiceGenerator.createService;
import static com.bitmake.open.api.client.ApiServiceGenerator.executeSync;

/**
 * account service impl
 */
public class AccountServiceImpl implements AccountService {

    private final AccountServiceRetrofit accountServiceRetrofit;

    public AccountServiceImpl(BitmakeApiConfig config) {
        accountServiceRetrofit = createService(AccountServiceRetrofit.class, config);
    }

    @Override
    public GetApiKeyResponse getApiKey() {
        return executeSync(accountServiceRetrofit.getApiKey());
    }

    @Override
    public List<AccountBalanceResponse> getAccountBalance() {
        return executeSync(accountServiceRetrofit.getAccountBalance());
    }

    @Override
    public MarginInfoResponse getMarginInfo(String symbol) {
        return executeSync(accountServiceRetrofit.getMarginInfo(symbol));
    }

    @Override
    public SetLeverageResponse setLeverage(SetLeverageRequest request) {
        return executeSync(accountServiceRetrofit.setLeverage(request));
    }

    @Override
    public List<PositionInfoResponse> getPositions(String symbol) {
        return executeSync(accountServiceRetrofit.getPositions(symbol));
    }
}
