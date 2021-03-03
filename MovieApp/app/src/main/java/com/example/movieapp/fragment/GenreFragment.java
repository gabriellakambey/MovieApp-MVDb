package com.example.movieapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.adapter.ListGenreAdapter;
import com.example.movieapp.model.GetMovieGenresModel;
import com.example.movieapp.model.MovieGenresModel;
import com.example.movieapp.retrofit.MovieService;
import com.example.movieapp.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.movieapp.retrofit.RetrofitInstance.API_KEY;

public class GenreFragment extends Fragment {

    private ListGenreAdapter listGenreAdapter;
    private RecyclerView list_genre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_genre, container, false);

        list_genre = view.findViewById(R.id.list_genre);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list_genre.setLayoutManager(layoutManager);

        MovieService movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        Call<GetMovieGenresModel> call = movieService.getGenres(API_KEY);
        call.enqueue(new Callback<GetMovieGenresModel>() {
            @Override
            public void onResponse(Call<GetMovieGenresModel> call, Response<GetMovieGenresModel> response) {
                GetMovieGenresModel getMovieGenresModel = response.body();
                List<MovieGenresModel> movieGenresModels = getMovieGenresModel.getMovieGenre();
                listGenreAdapter = new ListGenreAdapter(getActivity(), movieGenresModels);
                list_genre.setAdapter(listGenreAdapter);
            }

            @Override
            public void onFailure(Call<GetMovieGenresModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}