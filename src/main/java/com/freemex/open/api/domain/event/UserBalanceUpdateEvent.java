package com.freemex.open.api.domain.event;

import lombok.Data;

@Data
public class UserBalanceUpdateEvent {
    private String eventType;
    private Long eventTime;
    private Integer seqId;
    private UserBalanceItem data;
}
