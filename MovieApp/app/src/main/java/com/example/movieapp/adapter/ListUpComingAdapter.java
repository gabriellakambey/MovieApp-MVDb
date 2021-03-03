package com.example.movieapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ListUpComingAdapter extends RecyclerView.Adapter<ListUpComingAdapter.ViewHolder> {
    private Context context;
    private List<MovieModel> movieModels;
//    final private ListItemClickListener monClickListener;

    public ListUpComingAdapter(Context context, List<MovieModel> movieModels) {
        this.movieModels = movieModels;
//        monClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel movieModel = movieModels.get(position);

        Picasso.get().load(RetrofitInstance.IMG_URL + movieModel.getPoster_path())
                .placeholder(R.drawable.ic_image_broken1)
                .error(R.drawable.ic_image_broken2)
                .into(holder.iv_rekom);
        holder.tv_rekom_title.setText(movieModel.getOriginal_title());
    }

    @Override
    public int getItemCount() { return movieModels.size(); }

    public interface ListItemClickListener {
        void onPhoneListClick(int clickedItemIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView iv_rekom;
        public TextView tv_rekom_title;
        public RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_rekom = itemView.findViewById(R.id.iv_rekom);
            tv_rekom_title = itemView.findViewById(R.id.tv_rekom_title);
            relativeLayout = itemView.findViewById(R.id.background_color);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            //monClickListener.onPhoneListClick(clickedPosition);
        }
    }
}
