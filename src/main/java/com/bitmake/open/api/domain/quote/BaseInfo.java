package com.bitmake.open.api.domain.quote;

import lombok.Data;

@Data
public class BaseInfo {
    /**
     * current server timestamp
     */
    private Long serverTime;
    /**
     * current client ip
     */
    private String clientIp;
}
