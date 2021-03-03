package com.example.movieapp.retrofit;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String API_KEY = "6e171560323973a36f09ab0c075a8f9b";
    public static final String IMG_URL = "https://image.tmdb.org/t/p/original";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory
                            .create(new GsonBuilder().create()))
                    .build();

        }

        return retrofit;
    }
}
