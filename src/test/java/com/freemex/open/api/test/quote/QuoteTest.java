package com.freemex.open.api.test.quote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemex.open.api.domain.quote.*;
import com.freemex.open.api.service.quote.QuoteService;
import com.freemex.open.api.service.quote.impl.QuoteServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class QuoteTest extends QuoteBaseTest {
    private QuoteService quoteService;

    @Before
    public void before() {
        this.config = this.config();
        this.quoteService = new QuoteServiceImpl(this.config);
    }

    @Test
    public void getQuoteIndex() {
        QuoteResponse<List<QuoteIndex>> response = this.quoteService.getQuoteIndex(SYMBOL_FUTURE);
        toResultString("result", response);
    }

    /**
     * fix
     */
    @Test
    public void getQuoteDepth() {
        QuoteResponse<List<QuoteDepth>> response = this.quoteService.getQuoteDepth(SYMBOL, null, null);
        toResultString("result", response);
    }

    /**
     * fix
     */
    @Test
    public void getQuoteKline() {
        QuoteResponse<List<QuoteKline>> response = this.quoteService.getQuoteKline(SYMBOL, "1m", 10, System.currentTimeMillis());
        toResultString("result", response);
    }

    /**
     * fix
     */
    @Test
    public void getQuoteTrade() {
        QuoteResponse<List<QuoteTrade>> response = this.quoteService.getQuoteLastTrade(SYMBOL, 10);
        toResultString("result", response);
    }


    @Test
    public void getQuoteTicker() {
        QuoteResponse<List<QuoteTicker>> response = this.quoteService.getQuoteTicker(SYMBOL);
        toResultString("result", response);
    }
}
