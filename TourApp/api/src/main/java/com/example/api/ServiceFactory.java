package com.example.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceFactory {

    public <T> T build(Class<T> theClass) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit restAdapter = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://tourscraping.appspot.com")
                .build();

        return restAdapter.create(theClass);
    }
}
