package com.bitmake.open.api.domain.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteTicker {
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
     * close
     */
    @JsonProperty("c")
    private String close;

    /**
     * high
     */
    @JsonProperty("h")
    private String high;

    /**
     * low
     */
    @JsonProperty("l")
    private String low;

    /**
     * open
     */
    @JsonProperty("o")
    private String open;

    /**
     * volume
     */
    @JsonProperty("v")
    private String volume;

    /**
     * amount
     */
    @JsonProperty("qv")
    private String amount;

    /**
     * Chg
     */
    @JsonProperty("m")
    private String chg;
}
