package com.example.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.MovieModel;

import java.util.List;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder> {

    private List<MovieModel> movieModels;
    private Context context;

    public ListMovieAdapter(Context context, List<MovieModel> movieModels){
        this.context = context;
        this.movieModels = movieModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_genre, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position) {
        MovieModel movieModel = movieModels.get(position);

        holder.tv_title.setText(movieModel.getOriginal_title());
        holder.tv_overview.setText(movieModel.getPoster_path());

    }

    @Override
    public int getItemCount() { return movieModels.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_title;
        public TextView tv_overview;

        public ViewHolder(View itemView){
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_overview = itemView.findViewById(R.id.tv_overview);
        }
    }


}
