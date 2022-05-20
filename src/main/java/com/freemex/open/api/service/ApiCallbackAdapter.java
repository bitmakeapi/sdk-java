package com.freemex.open.api.service;

import com.freemex.open.api.constant.APIConstants;
import com.freemex.open.api.exception.FreemexApiError;
import com.freemex.open.api.exception.FreemexApiException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

import static com.freemex.open.api.client.ApiServiceGenerator.getApiError;

public class ApiCallbackAdapter<T> implements Callback<T> {

    private final ApiCallback<T> callback;

    public ApiCallbackAdapter(ApiCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            callback.onResponse(response.body());
        } else {
            if (response.code() == APIConstants.HTTP_CODE_TIMEOUT) {
                // HTTP 504 return code is used when the API successfully sent the message but not get a response within the timeout period.
                // It is important to NOT treat this as a failure; the execution status is UNKNOWN and could have been a success.
                return;
            }
            try {
                FreemexApiError apiError = getApiError(response);
                onFailure(call, new FreemexApiException(apiError));
            } catch (IOException e) {
                onFailure(call, new FreemexApiException(e));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        if (throwable instanceof FreemexApiException) {
            callback.onFailure(throwable);
        } else {
            callback.onFailure(new FreemexApiException(throwable));
        }
    }
}
