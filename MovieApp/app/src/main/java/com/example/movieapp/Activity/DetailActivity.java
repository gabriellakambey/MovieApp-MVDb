package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp.R;
import com.example.movieapp.model.DetailModel;
import com.example.movieapp.model.GetDetailModel;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.retrofit.MovieService;
import com.example.movieapp.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.movieapp.retrofit.RetrofitInstance.API_KEY;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    int id;
    String original_title;
    ImageView iv_backdrop, iv_detailPoster;
    TextView tv_titleDetail, tv_rateDetail, tv_runtimeDetail, tv_relaseDetail, tv_overview;
    MovieModel movieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        iv_backdrop = findViewById(R.id.iv_backdrop);
        iv_detailPoster = findViewById(R.id.iv_detailPoster);
        tv_titleDetail = findViewById(R.id.tv_titleDetail);
        tv_rateDetail = findViewById(R.id.tv_rateDetail);
        tv_runtimeDetail = findViewById(R.id.tv_runtimeDetail);
        tv_relaseDetail = findViewById(R.id.tv_relaseDetail);
        tv_overview = findViewById(R.id.tv_overview);


        movieModel = getIntent().getParcelableExtra(EXTRA_MOVIE);
        id = movieModel.getId();
        original_title = movieModel.getOriginal_title();

        MovieService movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        Call<DetailModel> call = movieService.getDetail(API_KEY, id);
        call.enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                DetailModel detailModel = response.body();

                Picasso.get().load(RetrofitInstance.IMG_URL+detailModel.getBackdrop_path())
                        .placeholder(R.drawable.ic_image_broken1)
                        .error(R.drawable.ic_image_broken2)
                        .into(iv_backdrop);
                Picasso.get().load(RetrofitInstance.IMG_URL+detailModel.getPoster_path())
                        .placeholder(R.drawable.ic_image_broken1)
                        .error(R.drawable.ic_image_broken2)
                        .into(iv_detailPoster);

                tv_titleDetail.setText(detailModel.getOriginal_title());
                tv_rateDetail.setText((detailModel.getVote_average()) + "/10");
                tv_runtimeDetail.setText((detailModel.getRuntime())+" menit");
                tv_relaseDetail.setText(detailModel.getRelase_date().substring(0, 4));
                tv_overview.setText(detailModel.getOverview());
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {

            }
        });
    }
}