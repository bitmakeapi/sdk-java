package com.bitmake.open.api.service.account;

import com.bitmake.open.api.domain.account.*;

import java.util.List;

/**
 * account service
 */
public interface AccountService {

    /**
     * Fetch current account balance
     *
     * @return
     */
    List<AccountBalanceResponse> getAccountBalance();

    /**
     * Fetch margin info
     *
     * @param symbol symbol name. required
     * @return
     */
    MarginInfoResponse getMarginInfo(String symbol);

    /**
     * Set position leverage
     *
     * @param request {symbol:"symbol name", leverage:10}
     * @return
     */
    SetLeverageResponse setLeverage(SetLeverageRequest request);

    /**
     * Fetch positions
     *
     * @param symbol symbol name. if it is empty, all position data will be returned
     * @return
     */
    List<PositionInfoResponse> getPositions(String symbol);
}
