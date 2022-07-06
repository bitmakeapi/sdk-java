package com.bitmake.open.api.service.quote.impl;

import com.bitmake.open.api.domain.quote.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface QuoteServiceRetrofit {

    /**
     * Fetch index price
     *
     * @param symbol
     * @return
     */
    @GET("/t/v1/quote/index")
    Call<List<QuoteIndex>> getQuoteIndex(@Query("symbol") String symbol);

    /**
     * Fetch order book
     *
     * @param symbol    symbol
     * @param dumpScale merged price precision, default to take the symbol precision.
     * @param limit     Number of data items. The maximum value is 100. The default value is 100.
     * @return
     */
    @GET("/t/v1/quote/depth")
    Call<List<QuoteDepth>> getQuoteDepth(@Query("symbol") String symbol, @Query("dumpScale") Integer dumpScale
            , @Query("limit") Integer limit);

    /**
     * Fetch quote kline(candlestick)
     *
     * @param symbol   symbol
     * @param interval kline type:1m,5m,15m,30m,1h,1d,1w,1M
     * @param limit    Number of data items. The maximum value is 1000. The default value is 1000
     * @param to       The time of the last item. Default is the current time
     * @return
     */
    @GET("/t/v1/quote/klines")
    Call<List<QuoteKline>> getQuoteKline(@Query("symbol") String symbol, @Query("interval") String interval
            , @Query("limit") Integer limit, @Query("to") Long to);

    /**
     * Fetch quote trades
     *
     * @param symbol symbol
     * @param limit  Number of data items. The maximum value is 100. The default value is 100
     * @return
     */
    @GET("/t/v1/quote/trades")
    Call<List<QuoteTrade>> getQuoteLastTrade(@Query("symbol") String symbol, @Query("limit") Integer limit);

    /**
     * Fetch quote ticker
     *
     * @param symbol Multiple symbol are separated by ','
     * @return
     */
    @GET("/t/v1/quote/ticker")
    Call<List<QuoteTicker>> getQuoteTicker(@Query("symbol") String symbol);
}
