package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.model.LoginResponseModel;
import com.example.movieapp.retrofit.MovieService;
import com.example.movieapp.retrofit.RetrofitLoginRegis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword;
    Button btnCancel, btnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnCancel = findViewById(R.id.btn_cancel);
        btnRegis = findViewById(R.id.btn_register);

        btnRegis.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            MovieService movieService = RetrofitLoginRegis.getRetrofit().create(MovieService.class);
            Call<LoginResponseModel> call = movieService.register(name, email, password);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    LoginResponseModel loginResponseModel = response.body();
                    if (loginResponseModel.getStatus().equals("success")) {
                        Toast.makeText(RegisActivity.this, "Success Register " + loginResponseModel.getUserModel().getUserName(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisActivity.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    Toast.makeText(RegisActivity.this, "Failed Register " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}