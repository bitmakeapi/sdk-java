package com.bitmake.open.api.utils;

import com.bitmake.open.api.constant.APIConstants;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignUtils {
    /**
     * Signing a Message.<br/>
     * <p>
     * using: Hmac SHA256 + base64
     *
     * @param apiKey      user's api key
     * @param timestamp   timestamp of the request in milliseconds.
     *                    eg: 2018-03-08T10:59:25.789Z
     * @param method      eg: POST
     * @param requestPath eg: /orders
     * @param queryString eg: before%3D2%26limit%3D30
     * @param body        POST request json body string
     * @param secretKey   user's secret key
     * @return signed string
     * @throws CloneNotSupportedException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    public static String sign(String apiKey, String timestamp, String method, String requestPath,
                              String queryString, String body, String secretKey)
            throws CloneNotSupportedException, InvalidKeyException, UnsupportedEncodingException {
        if (StringUtils.isEmpty(secretKey) || StringUtils.isEmpty(method)) {
            return APIConstants.EMPTY;
        }

        String preHash = preHash(apiKey, timestamp, method, requestPath, queryString, body);
        byte[] secretKeyBytes = secretKey.getBytes(APIConstants.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
        Mac mac = (Mac) MAC.clone();
        mac.init(secretKeySpec);
        return new String(Hex.encodeHex(mac.doFinal(preHash.getBytes())));
    }

    /**
     * the prehash string = apikey + timestamp + method + requestPath + queryString
     *
     * @param apiKey      eg: user's api key
     * @param timestamp   timestamp of the request in milliseconds.
     *                    eg: 1634023939865
     * @param method      eg: POST
     * @param requestPath eg: /orders
     * @param queryString eg: before%3D2%26limit%3D30
     * @param body        POST request body json string
     * @return prehash string eg: apikey1634023939865POST/ordersbefore%3D2%26limit%3D30
     */
    public static String preHash(String apiKey, String timestamp, String method, String requestPath,
                                 String queryString, String body) {
        StringBuilder preHash = new StringBuilder();
        preHash.append(apiKey);
        preHash.append(timestamp);
        preHash.append(method.toUpperCase());
        preHash.append(requestPath);
        if (StringUtils.isNotEmpty(queryString)) {
            preHash.append(queryString);
        }
        if (StringUtils.isNotEmpty(body)) {
            preHash.append(body);
        }
        return preHash.toString();
    }

    public static Mac MAC;

    static {
        try {
            MAC = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeErrorException(new Error("Can't get Mac's instance."));
        }
    }
}
