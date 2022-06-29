package com.bitmake.open.api.domain.account;

import lombok.Data;

@Data
public class SetLeverageRequest {
    private String symbol;
    private Integer leverage;
}
