package com.bitmake.open.api.service.quote.impl;

import com.bitmake.open.api.config.BitmakeApiConfig;
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
    public QuoteResponse<List<QuoteIndex>> getQuoteIndex(String symbol) {
        return executeSync(quoteServiceRetrofit.getQuoteIndex(symbol));
    }

    @Override
    public QuoteResponse<List<QuoteDepth>> getQuoteDepth(String symbol, Integer dumpScale, Integer limit) {
        return executeSync(quoteServiceRetrofit.getQuoteDepth(symbol, dumpScale, limit));
    }

    @Override
    public QuoteResponse<List<QuoteKline>> getQuoteKline(String symbol, String interval, Integer limit, Long to) {
        return executeSync(quoteServiceRetrofit.getQuoteKline(symbol, interval, limit, to));
    }

    @Override
    public QuoteResponse<List<QuoteTrade>> getQuoteLastTrade(String symbol, Integer limit) {
        return executeSync(quoteServiceRetrofit.getQuoteLastTrade(symbol, limit));
    }

    @Override
    public QuoteResponse<List<QuoteTicker>> getQuoteTicker(String symbol) {
        return executeSync(quoteServiceRetrofit.getQuoteTicker(symbol));
    }
}
