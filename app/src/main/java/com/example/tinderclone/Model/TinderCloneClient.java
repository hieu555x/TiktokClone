package com.example.tinderclone.Model;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TinderCloneClient {

    private static final String BASE_URL = "https://randomuser.me/api/0.4/";

    public static Retrofit retrofit;

    public static Retrofit getInstance() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
        }
        return retrofit;
    }
}
