package com.freemex.open.api.domain.event;

import lombok.Data;

@Data
public class UserTradeUpdateEvent {
    private String eventType;
    private Long eventTime;
    private Integer seqId;
    private UserTradeItem data;
}
