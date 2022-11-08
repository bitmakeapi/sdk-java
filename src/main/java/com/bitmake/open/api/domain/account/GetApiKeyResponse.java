package com.bitmake.open.api.domain.account;

import lombok.Data;

@Data
public class GetApiKeyResponse {

    private String name;
    private Long userId;
    private Long parentUserId;
    private Long accountId;
    private String ipWhiteList;

}

