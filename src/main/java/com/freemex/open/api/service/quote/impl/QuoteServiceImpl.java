package com.freemex.open.api.service.quote.impl;

import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.domain.quote.*;
import com.freemex.open.api.service.quote.QuoteService;

import java.util.List;

import static com.freemex.open.api.client.ApiServiceGenerator.createService;
import static com.freemex.open.api.client.ApiServiceGenerator.executeSync;

public class QuoteServiceImpl implements QuoteService {

    private final QuoteServiceRetrofit quoteServiceRetrofit;

    public QuoteServiceImpl(FreemexApiConfig config) {
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
