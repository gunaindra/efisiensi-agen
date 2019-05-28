package com.busefisensi.efisiensiagen.Transport;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class HTTPClient {
    public static void sendHTTPPostJSON(String url, String requestBody, final Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), requestBody);
        Request request = new Request.Builder().url(url).post(body).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                callback.onFailure(call, e);
                }
                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                callback.onResponse(call, response);
                }
        });
    }
    public static void sendHTTPGETJSON(String token, String url, final Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().header("Authorization", token).url(url).get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                callback.onFailure(call, e);
                }
                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                callback.onResponse(call, response);
                }
        });
    }
}
