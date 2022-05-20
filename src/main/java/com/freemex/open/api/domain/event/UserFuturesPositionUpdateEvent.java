package com.freemex.open.api.domain.event;

import lombok.Data;

@Data
public class UserFuturesPositionUpdateEvent {
    private String eventType;
    private Long eventTime;
    private Integer seqId;
    private UserFuturesPositionItem data;
}
