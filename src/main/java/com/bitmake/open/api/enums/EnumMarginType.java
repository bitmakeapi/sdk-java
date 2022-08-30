package com.bitmake.open.api.enums;

/**
 * margin type
 */
public enum EnumMarginType {
    ISOLATED(0),
    CROSS(1),
    ;

    private Integer type;

    EnumMarginType(Integer type) {
        this.type = type;
    }

    public Integer type() {
        return type;
    }
}
