package com.freemex.open.api.domain.event;

import lombok.Data;

import java.util.List;

@Data
public class UserOrderEvent {
    private String eventType;
    private Long eventTime;
    private Integer seqId;
    private List<UserOrderItem> data;
}
