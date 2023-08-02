package com.bitmake.open.api.service.account.impl;

import com.bitmake.open.api.client.ApiServiceGenerator;
import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.domain.account.*;
import com.bitmake.open.api.service.account.AccountService;

import java.util.List;

/**
 * account service impl
 */
public class AccountServiceImpl implements AccountService {

    private final AccountServiceRetrofit accountServiceRetrofit;

    public AccountServiceImpl(BitmakeApiConfig config) {
        accountServiceRetrofit = ApiServiceGenerator.createService(AccountServiceRetrofit.class, config);
    }

    @Override
    public GetApiKeyResponse getApiKey() {
        return ApiServiceGenerator.executeSync(accountServiceRetrofit.getApiKey());
    }

    @Override
    public List<AccountBalanceResponse> getAccountBalance() {
        return ApiServiceGenerator.executeSync(accountServiceRetrofit.getAccountBalance());
    }

    @Override
    public MarginInfoResponse getMarginInfo(String symbol) {
        return ApiServiceGenerator.executeSync(accountServiceRetrofit.getMarginInfo(symbol));
    }

    @Override
    public SetLeverageResponse setLeverage(SetLeverageRequest request) {
        return ApiServiceGenerator.executeSync(accountServiceRetrofit.setLeverage(request));
    }

    @Override
    public List<PositionInfoResponse> getPositions(String symbol) {
        return ApiServiceGenerator.executeSync(accountServiceRetrofit.getPositions(symbol));
    }
}
