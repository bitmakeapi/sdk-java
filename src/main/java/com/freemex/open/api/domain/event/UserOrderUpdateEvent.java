package com.freemex.open.api.domain.event;

import lombok.Data;

@Data
public class UserOrderUpdateEvent {
    private String eventType;
    private Long eventTime;
    private Integer seqId;
    private UserOrderItem data;
}
