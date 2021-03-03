package com.example.movieapp.retrofit;

import com.example.movieapp.model.DetailModel;
import com.example.movieapp.model.GetMovieGenresModel;
import com.example.movieapp.model.GetMovieModel;
import com.example.movieapp.model.GetPopularModel;
import com.example.movieapp.model.GetTopRatedModel;
import com.example.movieapp.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/upcoming")
    Call<GetMovieModel> getUpcoming(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<GetTopRatedModel> getTopRated(@Query("api_key") String api_key);

    @GET("movie/popular")
    Call<GetPopularModel> getPopular(@Query("api_key") String api_key);

    @GET("movie/now_playing")
    Call<GetMovieModel> getNowPlaying(@Query("api_key") String api_key);

    @GET("movie/{id}")
    Call<DetailModel> getDetail(@Query("api_key") String api_key, @Path("id") int id);

    @GET("genre/movie/list")
    Call<GetMovieGenresModel> getGenres(@Query("api_key") String api_key);

    @POST("register")
    Call<LoginResponseModel> register(@Field("name") String name, @Field("email") String email,
                                       @Field("password") String password);

    @GET("login")
    Call<LoginResponseModel> login(@Query("email") String email, @Query("password") String password);
}
