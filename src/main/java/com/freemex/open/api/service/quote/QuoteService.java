package com.freemex.open.api.service.quote;

import com.freemex.open.api.domain.quote.*;

import java.util.List;

public interface QuoteService {
    /**
     * Fetch index price
     *
     * @param symbol
     * @return
     */
    QuoteResponse<List<QuoteIndex>> getQuoteIndex(String symbol);

    /**
     * Fetch order book
     *
     * @param symbol    symbol
     * @param dumpScale Merge precision, default to take the symbol precision
     * @param limit     Number of data items. The maximum value is 100. The default value is 100
     * @return
     */
    QuoteResponse<List<QuoteDepth>> getQuoteDepth(String symbol, Integer dumpScale, Integer limit);

    /**
     * Fetch quote kline(candlestick)
     *
     * @param symbol   symbol
     * @param interval kline type:1m,5m,15m,30m,1h,1d,1w,1M
     * @param limit    Number of data items. The maximum value is 1000. The default value is 1000
     * @param to       The time of the last item. Default is the current time
     * @return
     */
    QuoteResponse<List<QuoteKline>> getQuoteKline(String symbol, String interval, Integer limit, Long to);

    /**
     * Fetch quote trades
     *
     * @param symbol symbol
     * @param limit  Number of data items. The maximum value is 100. The default value is 100
     * @return
     */
    QuoteResponse<List<QuoteTrade>> getQuoteLastTrade(String symbol, Integer limit);

    /**
     * Fetch quote ticker
     *
     * @param symbol Multiple symbol are separated by ','
     * @return
     */
    QuoteResponse<List<QuoteTicker>> getQuoteTicker(String symbol);
}
