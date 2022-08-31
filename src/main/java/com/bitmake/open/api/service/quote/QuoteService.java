package com.bitmake.open.api.service.quote;

import com.bitmake.open.api.domain.quote.*;

import java.util.List;

public interface QuoteService {
    /**
     * Fetch index price
     *
     * @param symbol if it is empty, all index data will be returned
     * @return
     */
    List<QuoteIndex> getQuoteIndex(String symbol);

    /**
     * Fetch order book
     *
     * @param symbol     symbol. required
     * @param dumpScale  Merge precision, default to take the symbol precision,null means default
     * @param depthLevel Number of data items. The maximum value is 100. The default value is 20,null means default
     * @return
     */
    List<QuoteDepth> getQuoteDepth(String symbol, Integer dumpScale, Integer depthLevel);

    /**
     * Fetch order book
     *
     * @param symbol    symbol. required
     * @return
     */
    List<QuoteDepth> getQuoteDepth(String symbol);

    /**
     * Fetch quote kline(candlestick)
     *
     * @param symbol   symbol. required
     * @param interval kline type:1m,5m,15m,30m,1h,1d,1w,1M
     * @param limit    Number of data items. The maximum value is 1000. The default value is 1000,null means default
     * @param to       The time of the last item. default is the current time,null means default
     * @return
     */
    List<QuoteKline> getQuoteKline(String symbol, String interval, Integer limit, Long to);

    /**
     * Fetch quote kline(candlestick)
     *
     * @param symbol   symbol. required
     * @param interval kline type:1m,5m,15m,30m,1h,1d,1w,1M
     * @return
     */
    List<QuoteKline> getQuoteKline(String symbol, String interval);

    /**
     * Fetch quote trades
     *
     * @param symbol symbol. required
     * @param limit  Number of data items. The maximum value is 100. The default value is 100,null means default
     * @return
     */
    List<QuoteTrade> getQuoteLastTrade(String symbol, Integer limit);

    /**
     * Fetch quote trades
     *
     * @param symbol symbol. required
     * @return
     */
    List<QuoteTrade> getQuoteLastTrade(String symbol);

    /**
     * Fetch quote ticker
     *
     * @param symbol if it is empty, all ticker data will be returned,multiple symbol are separated by ','
     * @return
     */
    List<QuoteTicker> getQuoteTicker(String symbol);
}
