package com.bitmake.open.api.domain.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteBookTicker {
    /**
     * timestamp(millisecond)
     */
    @JsonProperty("t")
    private Long timestamp;

    /**
     * symbol
     */
    @JsonProperty("s")
    private String symbol;

    /**
     * best bid price
     */
    @JsonProperty("b")
    private String bidPrice;

    /**
     * best bid quantity
     */
    @JsonProperty("B")
    private String bidQuantity;

    /**
     * best ask price
     */
    @JsonProperty("a")
    private String askPrice;

    /**
     * best ask quantity
     */
    @JsonProperty("A")
    private String askQuantity;
}
