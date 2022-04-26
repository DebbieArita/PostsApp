package com.example.retrorecytry3;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://my-json-server.typicode.com/DebbieArita/users.json/";

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    public static JSONPlaceholder getRetrofitInstance() {

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(JSONPlaceholder.class);
    }
}

