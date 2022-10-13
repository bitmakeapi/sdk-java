package com.bitmake.open.api.service.quote.impl;

import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.constant.APIConstants;
import com.bitmake.open.api.domain.quote.*;
import com.bitmake.open.api.service.quote.QuoteService;

import java.util.List;

import static com.bitmake.open.api.client.ApiServiceGenerator.createService;
import static com.bitmake.open.api.client.ApiServiceGenerator.executeSync;

public class QuoteServiceImpl implements QuoteService {

    private final QuoteServiceRetrofit quoteServiceRetrofit;

    public QuoteServiceImpl(BitmakeApiConfig config) {
        quoteServiceRetrofit = createService(QuoteServiceRetrofit.class, config);
    }

    @Override
    public BaseInfo getBaseInfo() {
        return executeSync(quoteServiceRetrofit.getBaseInfo());
    }

    @Override
    public List<SymbolInfo> getAllSymbol(){
        return executeSync(quoteServiceRetrofit.getAllSymbol());
    }

    @Override
    public List<QuoteIndex> getQuoteIndex(String symbol) {
        return executeSync(quoteServiceRetrofit.getQuoteIndex(symbol));
    }

    @Override
    public List<QuoteDepth> getQuoteDepth(String symbol, Integer dumpScale, Integer depthLevel) {
        return executeSync(quoteServiceRetrofit.getQuoteDepth(symbol, dumpScale, depthLevel));
    }

    @Override
    public List<QuoteDepth> getQuoteDepth(String symbol) {
        return executeSync(quoteServiceRetrofit.getQuoteDepth(symbol, null, APIConstants.DEFAULT_LIMIT.intValue()));
    }

    @Override
    public List<QuoteKline> getQuoteKline(String symbol, String interval, Integer limit, Long to) {
        return executeSync(quoteServiceRetrofit.getQuoteKline(symbol, interval, limit, to));
    }

    @Override
    public List<QuoteKline> getQuoteKline(String symbol, String interval) {
        return executeSync(quoteServiceRetrofit.getQuoteKline(symbol, interval, null, null));
    }

    @Override
    public List<QuoteTrade> getQuoteLastTrade(String symbol, Integer limit) {
        return executeSync(quoteServiceRetrofit.getQuoteLastTrade(symbol, limit));
    }

    @Override
    public List<QuoteTrade> getQuoteLastTrade(String symbol) {
        return executeSync(quoteServiceRetrofit.getQuoteLastTrade(symbol, APIConstants.DEFAULT_LIMIT.intValue()));
    }

    @Override
    public List<QuoteTicker> getQuoteTicker(String symbol) {
        return executeSync(quoteServiceRetrofit.getQuoteTicker(symbol));
    }
}
