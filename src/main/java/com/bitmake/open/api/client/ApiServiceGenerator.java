package com.bitmake.open.api.client;

import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.exception.BitmakeApiError;
import com.bitmake.open.api.exception.BitmakeApiException;
import com.bitmake.open.api.security.AuthenticationInterceptor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
            .configure(JsonParser.Feature.ALLOW_COMMENTS, true)
            .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    private static final Converter.Factory CONVERTER_FACTORY = JacksonConverterFactory.create(objectMapper);

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
    private static final Converter<ResponseBody, BitmakeApiError> errorBodyConverter =
            (Converter<ResponseBody, BitmakeApiError>) CONVERTER_FACTORY.responseBodyConverter(
                    BitmakeApiError.class, new Annotation[0], null);

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, BitmakeApiConfig configuration) {
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
                BitmakeApiError apiError = getApiError(response);
                throw new BitmakeApiException(apiError);
            }
        } catch (IOException e) {
            throw new BitmakeApiException(e);
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public static BitmakeApiError getApiError(final Response<?> response) throws IOException, BitmakeApiException {

        if (response.errorBody() != null) {
            try {
                return errorBodyConverter.convert(response.errorBody());
            } catch (Exception ex) {
            }
        }

        BitmakeApiError apiError = new BitmakeApiError();
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
