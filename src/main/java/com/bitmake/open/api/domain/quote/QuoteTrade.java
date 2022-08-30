package com.bitmake.open.api.domain.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteTrade {

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
     * quantity
     */
    @JsonProperty("q")
    private String quantity;

    /**
     * is Maker
     */
    @JsonProperty("m")
    private Boolean isMaker;

    /**
     * price
     */
    @JsonProperty("p")
    private String price;

    /**
     * trade id
     */
    @JsonProperty("v")
    private String tradeId;
}
