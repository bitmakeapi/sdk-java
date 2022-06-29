package com.bitmake.open.api.exception;

public class BitmakeApiException extends RuntimeException {

    private static final long serialVersionUID = 3788669840036201041L;
    /**
     * Error response object returned by bitmake API.
     */
    private BitmakeApiError error;

    /**
     * Instantiates a new bitmake api exception.
     *
     * @param error an error response object
     */
    public BitmakeApiException(BitmakeApiError error) {
        this.error = error;
    }

    /**
     * Instantiates a new bitmake api exception.
     */
    public BitmakeApiException() {
        super();
    }

    /**
     * Instantiates a new bitmake api exception.
     *
     * @param message the message
     */
    public BitmakeApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new bitmake api exception.
     *
     * @param cause the cause
     */
    public BitmakeApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new bitmake api exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BitmakeApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the response error object from bitmake API, or null if no response object was returned (e.g. server returned 500).
     */
    public BitmakeApiError getError() {
        return error;
    }

    @Override
    public String getMessage() {
        if (error != null) {
            return String.format("code:%s,msg:%s", error.getCode(), error.getMsg());
        }
        return super.getMessage();
    }
}
