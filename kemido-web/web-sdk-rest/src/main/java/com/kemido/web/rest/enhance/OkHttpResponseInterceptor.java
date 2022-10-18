package com.kemido.web.rest.enhance;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Objects;

/**
 * <p>Description: OkHttp 响应拦截器 </p>
 */
public class OkHttpResponseInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);

        if (response.code() == 200) {
            ResponseBody responseBody = response.body();
            if (responseBody != null && responseBody.contentLength() != 0 && Objects.requireNonNull(responseBody.contentType()).type().equals(MediaType.APPLICATION_JSON_VALUE)) {
                String str = responseBody.string();
                JsonObject jsonObject = JsonParser.parseString(str).getAsJsonObject();
                String data = jsonObject.get("data").getAsString();
                if (StringUtils.isNotBlank(data)) {
                    ResponseBody body = ResponseBody.create(data, okhttp3.MediaType.get(MediaType.APPLICATION_JSON_VALUE));
                    return response.newBuilder().body(body).build();
                }
            }
        }

        return response;
    }
}
