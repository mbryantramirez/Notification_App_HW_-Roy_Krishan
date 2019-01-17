package com.example.krishanroy.notification_app_hw_roy_krishan.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static final String BASE_URL = "https://pixabay.com/" ;
    private static Retrofit ourInstance;

    public static Retrofit getInstance() {
        if (ourInstance != null) {
            return ourInstance;
        }
        ourInstance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return ourInstance;
    }
    public RetrofitSingleton() {
    }
}
