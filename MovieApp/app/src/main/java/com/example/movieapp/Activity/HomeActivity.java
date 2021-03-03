package com.example.movieapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.MenuItem;

import com.example.movieapp.R;
import com.example.movieapp.adapter.ListUpComingAdapter;
import com.example.movieapp.fragment.ExploreFragment;
import com.example.movieapp.fragment.HomeFragment;
import com.example.movieapp.fragment.GenreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements ListUpComingAdapter.ListItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView menu_bawah;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //bottom nav
        menu_bawah = findViewById(R.id.menu_bawah);
        menu_bawah.setOnNavigationItemSelectedListener(this);

        // Explore Fragment as main fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_area, new HomeFragment()).commit();
    }

    Fragment fragment = null;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.home:
                fragment = new HomeFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area,fragment);
                fragmentTransaction.commit();
                break;

            case R.id.explore:
                fragment = new ExploreFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area,fragment);
                fragmentTransaction.commit();
                break;

            case R.id.genre:
                fragment = new GenreFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area,fragment);
                fragmentTransaction.commit();
                break;
        }
        return true;
    }

    @Override
    public void onPhoneListClick(int clickedItemIndex) {

    }
}