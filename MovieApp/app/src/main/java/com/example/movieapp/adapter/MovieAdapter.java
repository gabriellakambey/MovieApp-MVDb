package com.example.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Activity.DetailActivity;
import com.example.movieapp.R;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.movieapp.Activity.DetailActivity.EXTRA_MOVIE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private List<MovieModel> movieModels;

    public MovieAdapter(Context context, List<MovieModel> movieModels) {
        this.context = context;
        this.movieModels = movieModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        viewHolder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(parent.getContext(), DetailActivity.class);
            MovieModel movieModel = new MovieModel();
            movieModel.setId(movieModels.get(viewHolder.getAdapterPosition()).getId());
            movieModel.setOriginal_title(movieModels.get(viewHolder.getAdapterPosition()).getOriginal_title());

            intent.putExtra(EXTRA_MOVIE, movieModel);
            parent.getContext().startActivity(intent);
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        MovieModel movieModel = movieModels.get(position);

        Picasso.get().load(RetrofitInstance.IMG_URL + movieModel.getPoster_path())
                .placeholder(R.drawable.ic_image_broken1)
                .error(R.drawable.ic_image_broken2)
                .into(holder.iv_rekom);
        holder.tv_rekom_title.setText(movieModel.getOriginal_title());
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_rekom;
        public TextView tv_rekom_title;
        public RelativeLayout relativeLayout;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                iv_rekom = itemView.findViewById(R.id.iv_rekom);
                tv_rekom_title = itemView.findViewById(R.id.tv_rekom_title);
                relativeLayout = itemView.findViewById(R.id.background_color);
                cardView = itemView.findViewById(R.id.phone_cardview);
        }
    }

}
