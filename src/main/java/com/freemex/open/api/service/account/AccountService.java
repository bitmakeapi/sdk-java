package com.freemex.open.api.service.account;

import com.freemex.open.api.domain.account.*;

import java.util.List;

/**
 * account service
 */
public interface AccountService {

    /**
     * Fetch account balance
     *
     * @return
     */
    List<AccountBalanceResponse> getAccountBalance();

    /**
     * Fetch margin info
     *
     * @param symbol
     * @return
     */
    MarginInfoResponse getMarginInfo(String symbol);

    /**
     * Set position leverage
     *
     * @param request
     * @return
     */
    SetLeverageResponse setLeverage(SetLeverageRequest request);

    /**
     * Fetch positions
     *
     * @param symbol
     * @return
     */
    List<PositionInfoResponse> getPositions(String symbol);
}
