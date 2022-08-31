package com.bitmake.open.api.constant;

import okhttp3.MediaType;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class APIConstants {
    public static final int HTTP_CODE_TIMEOUT = 504;
    /**
     * The default timeout is 30 seconds.
     */
    public static final long TIMEOUT = 1000 * 30;

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String ACCEPT = "Accept";

    public static final String COOKIE = "Cookie";

    public static final String LOCALE = "locale=";

    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_JSON_UTF8 = "application/json; charset=UTF-8";

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static final String FM_API_KEY = "FM-API-KEY";
    public static final String FM_API_SIGN = "FM-API-SIGN";
    public static final String FM_API_TIMESTAMP = "FM-API-TIMESTAMP";
    public static final String FM_V_CODE = "vcode";

    public static final String QUESTION = "?";
    public static final String EMPTY = "";
    public static final String HLINE = "-";
    public static final String SLASH = "/";

    /**
     * Decorator to indicate that an endpoint requires a signature.
     */
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";

    /**
     * Default ToStringStyle used by toString methods.
     * Override this to change the output format of the overridden toString methods.
     * - Example ToStringStyle.JSON_STYLE
     */
    public static ToStringStyle TO_STRING_BUILDER_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    public static final String PING_MSG_KEY = "ping";

    public static final String PONG_MSG_KEY = "pong";

    /**
     * 心跳间隔 1分钟一次
     */
    public static final long HEART_BEAT_INTERVAL = 60 * 1000;

    public static final Long DEFAULT_LIMIT = 20L;

    public static final Long DEFAULT_FROM_ID = 0L;
}
