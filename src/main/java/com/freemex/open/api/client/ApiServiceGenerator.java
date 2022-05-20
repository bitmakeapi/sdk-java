package com.freemex.open.api.client;

import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.exception.FreemexApiError;
import com.freemex.open.api.exception.FreemexApiException;
import com.freemex.open.api.security.AuthenticationInterceptor;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

/**
 * create Retrofit service
 */
@Slf4j
public class ApiServiceGenerator {

    private static final OkHttpClient SHARED_CLIENT;
    private static final Converter.Factory CONVERTER_FACTORY = JacksonConverterFactory.create();

    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(200);
        dispatcher.setMaxRequests(200);
        SHARED_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .dispatcher(dispatcher)
                .build();
    }

    @SuppressWarnings("unchecked")
    private static final Converter<ResponseBody, FreemexApiError> errorBodyConverter =
            (Converter<ResponseBody, FreemexApiError>) CONVERTER_FACTORY.responseBodyConverter(
                    FreemexApiError.class, new Annotation[0], null);

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, FreemexApiConfig configuration) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(configuration.getEndpoint())
                .addConverterFactory(CONVERTER_FACTORY);


        if (configuration == null || StringUtils.isEmpty(configuration.getApiKey()) || StringUtils.isEmpty(configuration.getSecretKey())) {
            CommonHeaderInterceptor interceptor = new CommonHeaderInterceptor();
            OkHttpClient adaptedClient = SHARED_CLIENT.newBuilder().addInterceptor(interceptor).build();
            retrofitBuilder.client(adaptedClient);
        } else {
            // `adaptedClient` will use its own interceptor, but share thread pool etc with the 'parent' client
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(configuration);
            OkHttpClient adaptedClient = SHARED_CLIENT.newBuilder().addInterceptor(interceptor).build();
            retrofitBuilder.client(adaptedClient);
        }

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        try {
            final Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                FreemexApiError apiError = getApiError(response);
                throw new FreemexApiException(apiError);
            }
        } catch (IOException e) {
            throw new FreemexApiException(e);
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public static FreemexApiError getApiError(final Response<?> response) throws IOException, FreemexApiException {

        if (response.errorBody() != null) {
            try {
                return errorBodyConverter.convert(response.errorBody());
            } catch (Exception ex) {
            }
        }

        FreemexApiError apiError = new FreemexApiError();
        apiError.setCode(response.code());
        apiError.setMsg(Strings.nullToEmpty(response.message()));
        return apiError;
    }

    /**
     * Returns the shared OkHttpClient instance.
     */
    public static OkHttpClient getSharedClient() {
        return SHARED_CLIENT;
    }
}
