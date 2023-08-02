package com.bitmake.open.api.service.quote.impl;

import com.bitmake.open.api.client.ApiServiceGenerator;
import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.constant.APIConstants;
import com.bitmake.open.api.domain.quote.*;
import com.bitmake.open.api.service.quote.QuoteService;

import java.util.List;

public class QuoteServiceImpl implements QuoteService {

    private final QuoteServiceRetrofit quoteServiceRetrofit;

    public QuoteServiceImpl(BitmakeApiConfig config) {
        quoteServiceRetrofit = ApiServiceGenerator.createService(QuoteServiceRetrofit.class, config);
    }

    @Override
    public BaseInfo getBaseInfo() {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getBaseInfo());
    }

    @Override
    public List<SymbolInfo> getAllSymbol() {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getAllSymbol());
    }

    @Override
    public List<QuoteIndex> getQuoteIndex(String symbol) {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getQuoteIndex(symbol));
    }

    @Override
    public List<QuoteDepth> getQuoteDepth(String symbol, Integer dumpScale, Integer depthLevel) {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getQuoteDepth(symbol, dumpScale, depthLevel));
    }

    @Override
    public List<QuoteDepth> getQuoteDepth(String symbol) {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getQuoteDepth(symbol, null, APIConstants.DEFAULT_LIMIT.intValue()));
    }

    @Override
    public List<QuoteKline> getQuoteKline(String symbol, String interval, Integer limit, Long to) {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getQuoteKline(symbol, interval, limit, to));
    }

    @Override
    public List<QuoteKline> getQuoteKline(String symbol, String interval) {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getQuoteKline(symbol, interval, null, null));
    }

    @Override
    public List<QuoteTrade> getQuoteLastTrade(String symbol, Integer limit) {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getQuoteLastTrade(symbol, limit));
    }

    @Override
    public List<QuoteTrade> getQuoteLastTrade(String symbol) {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getQuoteLastTrade(symbol, APIConstants.DEFAULT_LIMIT.intValue()));
    }

    @Override
    public List<QuoteTicker> getQuoteTicker(String symbol) {
        return ApiServiceGenerator.executeSync(quoteServiceRetrofit.getQuoteTicker(symbol));
    }
}
