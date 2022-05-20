package com.freemex.open.api.service.account.impl;

import com.freemex.open.api.domain.account.MarginInfoResponse;
import com.freemex.open.api.domain.account.SetLeverageRequest;
import com.freemex.open.api.domain.account.SetLeverageResponse;
import com.freemex.open.api.domain.account.PositionInfoResponse;
import com.freemex.open.api.service.account.AccountService;
import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.domain.account.AccountBalanceResponse;

import java.util.List;

import static com.freemex.open.api.client.ApiServiceGenerator.createService;
import static com.freemex.open.api.client.ApiServiceGenerator.executeSync;

/**
 * account service impl
 */
public class AccountServiceImpl implements AccountService {

    private final AccountServiceRetrofit accountServiceRetrofit;

    public AccountServiceImpl(FreemexApiConfig config) {
        accountServiceRetrofit = createService(AccountServiceRetrofit.class, config);
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
