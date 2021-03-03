package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.model.LoginResponseModel;
import com.example.movieapp.retrofit.MovieService;
import com.example.movieapp.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btn_login;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        tv_register.setOnClickListener(v->{
            Intent register = new Intent(getApplicationContext(), RegisActivity.class);
            startActivity(register);
        });

        btn_login.setOnClickListener(v-> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            MovieService movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
            Call<LoginResponseModel> call = movieService.login(email, password);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    LoginResponseModel loginResponseModel = response.body();
                    if(loginResponseModel.getStatus().equals("success")){
                        Toast.makeText(LoginActivity.this, "Success Login "+loginResponseModel.getUserModel().getUserName(), Toast.LENGTH_SHORT).show();

                        Intent login = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(login);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "Failed Login ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Error Login "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });
    }
}