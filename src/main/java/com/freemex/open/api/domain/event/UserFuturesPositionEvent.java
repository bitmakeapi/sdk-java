package com.freemex.open.api.domain.event;

import lombok.Data;

import java.util.List;

@Data
public class UserFuturesPositionEvent {
    private String eventType;
    private Long eventTime;
    private Integer seqId;
    private List<UserFuturesPositionItem> data;
}
