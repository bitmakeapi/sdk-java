package com.freemex.open.api.service.account.impl;

import com.freemex.open.api.constant.APIConstants;
import com.freemex.open.api.domain.account.AccountBalanceResponse;
import com.freemex.open.api.domain.account.MarginInfoResponse;
import com.freemex.open.api.domain.account.SetLeverageRequest;
import com.freemex.open.api.domain.account.SetLeverageResponse;
import com.freemex.open.api.domain.account.PositionInfoResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AccountServiceRetrofit {
    /**
     * Fetch account balance
     *
     * @param
     * @return
     */
    @GET("/f/v1/account/balance")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<List<AccountBalanceResponse>> getAccountBalance();

    /**
     * Fetch margin info
     *
     * @param symbol
     * @return
     */
    @GET("/f/v1/account/margininfo")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<MarginInfoResponse> getMarginInfo(@Query("symbol") String symbol);

    /**
     * Set leverage
     *
     * @param request
     * @return
     */
    @POST("/f/v1/account/leverage")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<SetLeverageResponse> setLeverage(@Body SetLeverageRequest request);

    /**
     * get positions
     *
     * @param symbol
     * @return
     */
    @GET("/f/v1/account/futures_positions")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<List<PositionInfoResponse>> getPositions(@Query("symbol") String symbol);
}
