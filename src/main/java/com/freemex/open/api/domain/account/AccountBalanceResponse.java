package com.freemex.open.api.domain.account;

import lombok.Data;

/**
 * Account Balance information
 *
 * @author freemex
 */
@Data
public class AccountBalanceResponse {
    private String token;
    private String available;
    private String total;
}
