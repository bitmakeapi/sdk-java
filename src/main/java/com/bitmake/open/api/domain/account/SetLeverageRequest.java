package com.bitmake.open.api.domain.account;

import lombok.Data;

@Data
public class SetLeverageRequest {
    /**
     * symbol name. required
     */
    private String symbol;
    /**
     * leverage. required
     */
    private Integer leverage;
}
