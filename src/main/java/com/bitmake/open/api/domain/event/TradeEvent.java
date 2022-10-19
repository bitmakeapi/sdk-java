package com.bitmake.open.api.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bitmake.open.api.domain.quote.QuoteTrade;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TradeEvent {
    @JsonProperty("tp")
    private String topic;

    @JsonProperty("e")
    private String event;

    @JsonProperty("ps")
    private Map<String, String> params = new HashMap<>();

    @JsonProperty("d")
    private List<QuoteTrade> data;

    @JsonProperty("f")
    private Boolean isFull;
}
