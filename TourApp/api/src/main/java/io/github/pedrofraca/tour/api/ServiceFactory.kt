package io.github.pedrofraca.tour.api

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.github.pedrofraca.tour.api.model.Classification
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceFactory {
    fun <T> build(theClass: Class<T>?): T {
        val logging = HttpLoggingInterceptor()

        logging.level = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        val restAdapter = Retrofit.Builder()
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl("http://tourscraping.appspot.com")
                .build()
        return restAdapter.create(theClass)
    }
}