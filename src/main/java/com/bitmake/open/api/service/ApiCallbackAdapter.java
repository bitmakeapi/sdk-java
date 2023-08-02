package com.bitmake.open.api.service;

import com.bitmake.open.api.client.ApiServiceGenerator;
import com.bitmake.open.api.constant.APIConstants;
import com.bitmake.open.api.exception.BitmakeApiError;
import com.bitmake.open.api.exception.BitmakeApiException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

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
                BitmakeApiError apiError = ApiServiceGenerator.getApiError(response);
                onFailure(call, new BitmakeApiException(apiError));
            } catch (IOException e) {
                onFailure(call, new BitmakeApiException(e));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        if (throwable instanceof BitmakeApiException) {
            callback.onFailure(throwable);
        } else {
            callback.onFailure(new BitmakeApiException(throwable));
        }
    }
}
