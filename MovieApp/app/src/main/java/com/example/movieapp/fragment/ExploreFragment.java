package com.example.movieapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.R;
import com.example.movieapp.adapter.MovieAdapter;
import com.example.movieapp.model.GetMovieModel;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.retrofit.MovieService;
import com.example.movieapp.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.movieapp.retrofit.RetrofitInstance.API_KEY;

public class ExploreFragment extends Fragment {
    private MovieAdapter movieAdapter;
    private RecyclerView list_nowplay;
    private RecyclerView list_latest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View views = inflater.inflate(R.layout.fragment_explore, container, false);

        list_nowplay = views.findViewById(R.id.list_nowplay);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        list_nowplay.setLayoutManager(layoutManager);

        MovieService movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        Call<GetMovieModel> call = movieService.getNowPlaying(API_KEY);
        call.enqueue(new Callback<GetMovieModel>() {
            @Override
            public void onResponse(Call<GetMovieModel> call, Response<GetMovieModel> response) {
                GetMovieModel getMovieModel = response.body();
                List<MovieModel> movieModels= getMovieModel.getResults();
                movieAdapter = new MovieAdapter(getActivity(), movieModels);
                list_nowplay.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<GetMovieModel> call, Throwable t) {

            }
        });

        return views;
    }
}