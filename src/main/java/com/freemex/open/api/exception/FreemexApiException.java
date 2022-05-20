package com.freemex.open.api.exception;

public class FreemexApiException extends RuntimeException {

    private static final long serialVersionUID = 3788669840036201041L;
    /**
     * Error response object returned by freemex API.
     */
    private FreemexApiError error;

    /**
     * Instantiates a new freemex api exception.
     *
     * @param error an error response object
     */
    public FreemexApiException(FreemexApiError error) {
        this.error = error;
    }

    /**
     * Instantiates a new freemex api exception.
     */
    public FreemexApiException() {
        super();
    }

    /**
     * Instantiates a new freemex api exception.
     *
     * @param message the message
     */
    public FreemexApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new freemex api exception.
     *
     * @param cause the cause
     */
    public FreemexApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new freemex api exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public FreemexApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the response error object from freemex API, or null if no response object was returned (e.g. server returned 500).
     */
    public FreemexApiError getError() {
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
