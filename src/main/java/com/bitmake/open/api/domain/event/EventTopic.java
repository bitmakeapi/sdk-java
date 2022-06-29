package com.bitmake.open.api.domain.event;

public enum EventTopic {

    TRADE("trade"),
    REAL_TIMES("realtimes"),
    KLINE("kline"),
    INDEX("index"),
    DIFF_MERGED_DEPTH("diffMergedDepth"),
    BOOK_TICKER("bookTicker"),

    USER_BALANCE("balance"),
    USER_BALANCE_UPDATE("balanceUpdate"),
    USER_ORDER("order"),
    USER_ORDER_UPDATE("orderUpdate"),
    USER_FUTURES_POSITION("FuturesPosition"),
    USER_FUTURES_POSITION_UPDATE("FuturesPositionUpdate"),
    USER_TRADE_UPDATE("TradeUpdate"),
    ;

    private String topic;

    EventTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public String getTopic(String interval) {
        return String.format(this.getTopic(), interval);
    }
}
