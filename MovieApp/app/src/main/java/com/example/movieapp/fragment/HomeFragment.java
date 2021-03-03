package com.example.movieapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieapp.Activity.DetailActivity;
import com.example.movieapp.R;
import com.example.movieapp.adapter.ListUpComingAdapter;
import com.example.movieapp.adapter.MovieAdapter;
import com.example.movieapp.model.GetMovieModel;
import com.example.movieapp.model.GetPopularModel;
import com.example.movieapp.model.GetTopRatedModel;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.retrofit.MovieService;
import com.example.movieapp.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.movieapp.retrofit.RetrofitInstance.API_KEY;


public class  HomeFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private RecyclerView list_rekom;
    private RecyclerView list_toprated;
    private RecyclerView list_popular;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View views = inflater.inflate(R.layout.fragment_home, container, false);

        // create RecyclerView UpComing
        list_rekom = views.findViewById(R.id.list_rekom);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        list_rekom.setLayoutManager(layoutManager);

        MovieService movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        Call<GetMovieModel> call = movieService.getUpcoming(API_KEY);
        call.enqueue(new Callback<GetMovieModel>() {
            @Override
            public void onResponse(Call<GetMovieModel> call, Response<GetMovieModel> response) {
                GetMovieModel getMovieModel = response.body();
                List<MovieModel> movieModels = getMovieModel.getResults();
                movieAdapter = new MovieAdapter(getActivity(), movieModels);
                list_rekom.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<GetMovieModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // create RecyclerView TopRated
        list_toprated = views.findViewById(R.id.list_toprated);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        list_toprated.setLayoutManager(linearLayoutManager);

        MovieService movieService1 = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        Call<GetTopRatedModel> call1 = movieService1.getTopRated(API_KEY);
        call1.enqueue(new Callback<GetTopRatedModel>() {
            @Override
            public void onResponse(Call<GetTopRatedModel> call1, Response<GetTopRatedModel> response) {
                GetTopRatedModel getTopRatedModel = response.body();
                List<MovieModel> movieModels1 = getTopRatedModel.getResults();
                movieAdapter = new MovieAdapter(getActivity(), movieModels1);
                list_toprated.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<GetTopRatedModel> call, Throwable t1) {
                Toast.makeText(getActivity(), "Gagal mengambil data "+t1.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // create RecycleView Popular
        list_popular = views.findViewById(R.id.list_popular);

        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        list_popular.setLayoutManager(lm);

        MovieService ms = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        Call<GetPopularModel> call2 = ms.getPopular(API_KEY);
        call2.enqueue(new Callback<GetPopularModel>() {
            @Override
            public void onResponse(Call<GetPopularModel> call, Response<GetPopularModel> response) {
                GetPopularModel getPopularModel = response.body();
                List<MovieModel> movieModels2 = getPopularModel.getResults();
                movieAdapter = new MovieAdapter(getActivity(), movieModels2);
                list_popular.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<GetPopularModel> call, Throwable t2) {
                Toast.makeText(getActivity(), "Gagal mengambil data "+t2.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return views;
    }

}