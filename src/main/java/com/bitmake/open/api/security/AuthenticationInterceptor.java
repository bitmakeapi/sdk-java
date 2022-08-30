package com.bitmake.open.api.security;

import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.constant.APIConstants;
import com.bitmake.open.api.exception.BitmakeApiException;
import com.bitmake.open.api.utils.SignUtils;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.Objects;

public class AuthenticationInterceptor implements Interceptor {

    private BitmakeApiConfig config;

    public AuthenticationInterceptor(BitmakeApiConfig config) {
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();
        boolean isSignatureRequired = original.header(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED) != null;
        newRequestBuilder.removeHeader(APIConstants.FM_API_KEY)
                .removeHeader(APIConstants.FM_API_TIMESTAMP)
                .removeHeader(APIConstants.FM_API_SIGN);

        // Endpoint requires signing the payload
        if (isSignatureRequired) {
            final String timestamp = String.valueOf(System.currentTimeMillis());
            newRequestBuilder.addHeader(APIConstants.FM_API_KEY, config.getApiKey());
            newRequestBuilder.addHeader(APIConstants.FM_API_SIGN, this.sign(chain.request(), timestamp));
            newRequestBuilder.addHeader(APIConstants.FM_API_TIMESTAMP, timestamp);
        }

        // Build new request after adding the necessary authentication information
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }

    /**
     * Extracts the request body into a String.
     *
     * @return request body as a string
     */
    @SuppressWarnings("unused")
    private static String bodyToString(RequestBody request) {
        try (final Buffer buffer = new Buffer()) {
            final RequestBody copy = request;
            if (copy != null) {
                copy.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AuthenticationInterceptor that = (AuthenticationInterceptor) o;
        return Objects.equals(config.getApiKey(), that.config.getApiKey()) &&
                Objects.equals(config.getSecretKey(), that.config.getSecretKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(config.getApiKey(), config.getSecretKey());
    }

    private String sign(final Request request, final String timestamp) {
        final String sign;
        try {
            sign = SignUtils.sign(config.getApiKey(), timestamp, this.getRequestMethod(request), this.getRequestPath(request),
                    this.getQueryString(request), bodyToString(request.body()), config.getSecretKey());
        } catch (final IOException e) {
            throw new BitmakeApiException("Request get body io exception.", e);
        } catch (final CloneNotSupportedException e) {
            throw new BitmakeApiException("Hmac SHA256 Base64 Signature clone not supported exception.", e);
        } catch (final InvalidKeyException e) {
            throw new BitmakeApiException("Hmac SHA256 Base64 Signature invalid key exception.", e);
        }
        return sign;
    }

    /**
     * get request url
     *
     * @param request
     * @return
     */
    private String url(final Request request) {
        return request.url().toString();
    }

    /**
     * get request method
     *
     * @param request
     * @return
     */
    private String getRequestMethod(final Request request) {
        return request.method().toUpperCase();
    }

    /**
     * get request path
     *
     * @param request
     * @return
     */
    private String getRequestPath(final Request request) {
        String url = this.url(request);
        url = url.replace(config.getEndpoint(), APIConstants.EMPTY);
        String requestPath = url;
        if (requestPath.contains(APIConstants.QUESTION)) {
            requestPath = requestPath.substring(0, url.lastIndexOf(APIConstants.QUESTION));
        }
        if (config.getEndpoint().endsWith(APIConstants.SLASH)) {
            requestPath = APIConstants.SLASH + requestPath;
        }
        return requestPath;
    }

    private String getQueryString(final Request request) throws UnsupportedEncodingException {
        final String url = this.url(request);
        String queryString = APIConstants.EMPTY;
        if (url.contains(APIConstants.QUESTION)) {
            queryString = url.substring(url.lastIndexOf(APIConstants.QUESTION) + 1);
        }
        if (StringUtils.isEmpty(queryString)) {
            return APIConstants.EMPTY;
        }
        return queryString;
    }
}
