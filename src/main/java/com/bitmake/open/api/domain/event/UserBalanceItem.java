package com.bitmake.open.api.domain.event;

import lombok.Data;

@Data
public class UserBalanceItem {
    private String token;
    private String available;
    private String total;
}
