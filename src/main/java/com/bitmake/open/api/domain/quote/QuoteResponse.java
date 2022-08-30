package com.bitmake.open.api.domain.quote;

import lombok.Data;

@Data
public class QuoteResponse<T> {
    private Integer code;
    private String msg;
    private T data;
}
