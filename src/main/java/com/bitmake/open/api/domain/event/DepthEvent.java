package com.bitmake.open.api.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DepthEvent {
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
     * asks
     */
    @JsonProperty("a")
    private List<List<String>> asks;

    /**
     * bids
     */
    @JsonProperty("b")
    private List<List<String>> bids;

    /**
     * data version start
     */
    @JsonProperty("vs")
    private Long versionStart;

    /**
     * version  end
     */
    @JsonProperty("ve")
    private Long versionEnd;

    /**
     * is full data: True - Full false- diff
     */
    @JsonProperty("f")
    private Boolean isFull;
}
