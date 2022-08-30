package com.bitmake.open.api.domain.event;

public enum EventType {

    SUB("sub"),
    CANCEL("cancel"),
    CANCEL_ALL("cancel_all"),
    ;

    private String type;

    EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
