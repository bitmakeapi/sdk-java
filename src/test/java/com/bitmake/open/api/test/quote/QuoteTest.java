package com.bitmake.open.api.test.quote;

import com.bitmake.open.api.domain.quote.*;
import com.bitmake.open.api.service.quote.QuoteService;
import com.bitmake.open.api.service.quote.impl.QuoteServiceImpl;
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

    /**
     * quote: get  index
     */
    @Test
    public void getQuoteIndex() {
        List<QuoteIndex> response = this.quoteService.getQuoteIndex(SYMBOL_FUTURE);
        toResultString("result", response);
    }

    /**
     * quote: get depth
     */
    @Test
    public void getQuoteDepth() {
        List<QuoteDepth> response = this.quoteService.getQuoteDepth(SYMBOL, null, null);
        toResultString("result", response);
    }

    /**
     * quote: get kline
     */
    @Test
    public void getQuoteKline() {
        List<QuoteKline> response = this.quoteService.getQuoteKline(SYMBOL, "1m", 10, System.currentTimeMillis());
        toResultString("result", response);
    }

    /**
     * quote: get trade
     */
    @Test
    public void getQuoteTrade() {
        List<QuoteTrade> response = this.quoteService.getQuoteLastTrade(SYMBOL, 10);
        toResultString("result", response);
    }


    /**
     * quote: get ticker
     */
    @Test
    public void getQuoteTicker() {
        List<QuoteTicker> response = this.quoteService.getQuoteTicker(SYMBOL);
        toResultString("result", response);
    }
}
