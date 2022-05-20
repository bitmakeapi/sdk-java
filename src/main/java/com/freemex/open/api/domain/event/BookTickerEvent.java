package com.freemex.open.api.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.freemex.open.api.domain.quote.QuoteBookTicker;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BookTickerEvent {
    @JsonProperty("t")
    private String topic;

    @JsonProperty("e")
    private String event;

    @JsonProperty("ps")
    private Map<String, String> params = new HashMap<>();

    @JsonProperty("d")
    private QuoteBookTicker data;

    @JsonProperty("f")
    private Boolean isFull;
}
