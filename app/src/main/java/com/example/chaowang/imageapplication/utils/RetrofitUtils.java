package com.example.chaowang.imageapplication.utils;

import android.util.Log;

import com.example.chaowang.imageapplication.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static String BASE_URL = "https://api.douban.com/v2/movie/";

    private static RetrofitUtils instance;

    private ApiService api;

    private RetrofitUtils()
    {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("wangchao","Back = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        api = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return api;
    }

    public static RetrofitUtils getInstance()
    {
        if(null == instance)
        {
            synchronized (RetrofitUtils.class)
            {
                if(null == instance)
                {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public void setNull()
    {
        instance = null;
    }
}
