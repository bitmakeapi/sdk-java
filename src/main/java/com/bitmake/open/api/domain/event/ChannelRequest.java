package com.bitmake.open.api.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ChannelRequest {

    @JsonProperty("t")
    private String topic;

    @JsonProperty("e")
    private String event;

    @JsonProperty("ps")
    private Map<String, String> params = new HashMap<>();
}
