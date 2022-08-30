package com.bitmake.open.api.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserTradeUpdateEvent {
    @JsonProperty("e")
    private String eventType;
    @JsonProperty("E")
    private Long eventTime;
    @JsonProperty("s")
    private Integer seqId;
    @JsonProperty("d")
    private UserTradeItem data;
}
