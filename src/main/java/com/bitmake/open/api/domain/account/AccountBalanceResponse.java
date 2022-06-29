package com.bitmake.open.api.domain.account;

import lombok.Data;

/**
 * Account Balance information
 *
 * @author bitmake
 */
@Data
public class AccountBalanceResponse {
    private String token;
    private String available;
    private String total;
}
