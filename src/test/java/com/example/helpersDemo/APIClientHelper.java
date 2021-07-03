package com.example.helpersDemo;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class APIClientHelper {
    private static Retrofit  retrofit = null;

    static Retrofit getClient()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit;
    }
}
