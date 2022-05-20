package com.freemex.open.api.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.freemex.open.api.domain.quote.QuoteIndex;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class IndexEvent {
    @JsonProperty("t")
    private String topic;

    @JsonProperty("e")
    private String event;

    @JsonProperty("ps")
    private Map<String, String> params = new HashMap<>();

    @JsonProperty("d")
    private List<QuoteIndex> data;

    @JsonProperty("f")
    private Boolean isFull;
}
