package com.example.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.MovieGenresModel;

import java.util.List;

public class ListGenreAdapter extends RecyclerView.Adapter<ListGenreAdapter.ViewHolder> {
    private Context context;
    private List<MovieGenresModel> movieGenresModels;

    public ListGenreAdapter (Context context, List<MovieGenresModel> movieGenresModels) {
        this.context = context;
        this.movieGenresModels = movieGenresModels;
    }

    @NonNull
    @Override
    public ListGenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_genre, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListGenreAdapter.ViewHolder holder, int position) {
        MovieGenresModel movieGenresModel = movieGenresModels.get(position);

        holder.tv_title.setText(movieGenresModel.getName());
    }

    @Override
    public int getItemCount() {
        return movieGenresModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
