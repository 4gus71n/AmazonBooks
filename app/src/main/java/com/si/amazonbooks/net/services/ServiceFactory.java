package com.si.amazonbooks.net.services;


import android.content.Context;

import com.si.amazonbooks.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */

public class ServiceFactory {

    private final Context mContext;

    public ServiceFactory(Context context) {
        mContext = context;
    }

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     * @param clazz Java interface of the retrofit service
     * @param endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.computation());

        OkHttpClient.Builder client = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            //Added a logging to check out the http response
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);
        }
        final Retrofit retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl(endPoint)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T service = retrofit.create(clazz);

        return service;
    }

}
