package com.bitmake.open.api.enums;

public enum EnumI18n {
    ENGLISH("en_US"),
    SIMPLIFIED_CHINESE("zh_CN"),
    ;

    private String i18n;

    EnumI18n(String i18n) {
        this.i18n = i18n;
    }

    public String i18n() {
        return i18n;
    }
}
