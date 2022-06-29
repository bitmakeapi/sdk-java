package com.bitmake.open.api.test.account;

import com.bitmake.open.api.domain.account.*;
import com.bitmake.open.api.service.account.AccountAsyncService;
import com.bitmake.open.api.service.account.AccountService;
import com.bitmake.open.api.service.account.impl.AccountServiceImpl;
import com.bitmake.open.api.test.BaseTests;
import com.bitmake.open.api.service.account.impl.AccountAsyncServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AccountTest extends AccountBaseTest {
    private AccountService accountService;

    private AccountAsyncService accountAsyncService;

    @Before
    public void before() {
        this.config = this.config();
        this.accountService = new AccountServiceImpl(this.config);
        this.accountAsyncService = new AccountAsyncServiceImpl(config);
    }

    /**
     * Get Balance
     */
    @Test
    public void getAccountBalance() {
        List<AccountBalanceResponse> result = this.accountService.getAccountBalance();
        toResultString("result", result);
    }

    /**
     * Get Margin Info
     */
    @Test
    public void getMarginInfo() {
        MarginInfoResponse result = this.accountService.getMarginInfo(BaseTests.SYMBOL_FUTURE);
        toResultString("result", result);
    }

    @Test
    public void setLeverage() {
        SetLeverageRequest request = new SetLeverageRequest();
        request.setLeverage(5);
        request.setSymbol(BaseTests.SYMBOL);
        SetLeverageResponse result = this.accountService.setLeverage(request);
        toResultString("result", result);
    }

    /**
     * Get Positions
     */
    @Test
    public void getPositions() {
        List<PositionInfoResponse> result = this.accountService.getPositions(BaseTests.SYMBOL_FUTURE);
        toResultString("result", result);
    }

    /**
     * Async Get Balance
     */
    @Test
    public void asyncGetAccountBalance() throws Exception {
        this.accountAsyncService.getAccountBalance(response -> toResultString("result", response));
        System.out.println("sent the message");
        Thread.sleep(10000);
    }
}
