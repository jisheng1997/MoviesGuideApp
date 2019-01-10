package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainLogoutActivity extends BaseActivity {

    private ArrayList<Movie> MoviesDetails = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private Bundle bundle = new Bundle();
    private RecyclerView movies_list_recyclerView;
    private RecyclerView genre_recyclerView;
    private ImageView imageView;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        movies_list_recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this,MoviesDetails);
        movies_list_recyclerView.setAdapter(movieadapter);

    }

    @Override
    protected void initData() {
        Movie movie1 = new Movie("The Shawshank Redemption", R.drawable.m01);
        MoviesDetails.add(movie1);
        Movie movie2 = new Movie("Forrest Gump", R.drawable.m01);
        MoviesDetails.add(movie2);
        Movie movie3 = new Movie("Schindler's List", R.drawable.m01);
        MoviesDetails.add(movie3);
        Movie movie4 = new Movie("Hachi: A Dog's Tale", R.drawable.m01);
        MoviesDetails.add(movie4);
        Movie movie5 = new Movie("La leggenda del pianista sull'oceano", R.drawable.m01);
        MoviesDetails.add(movie5);
        Movie movie6 = new Movie("Inception", R.drawable.m01);
        MoviesDetails.add(movie6);
        Movie movie7 = new Movie("Inception", R.drawable.m01);
        MoviesDetails.add(movie7);
        Movie movie8 = new Movie("Inception", R.drawable.m01);
        MoviesDetails.add(movie8);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.main_logout);
//        genre_recyclerView = findViewById(R.id.gerne_RecyclerView);
        movies_list_recyclerView = findViewById(R.id.movies_list_RecyclerView);
        mDrawerLayout = findViewById(R.id.main_login_DrawerLayout);
        imageView = findViewById(R.id.main_login_title_ImageView);
        navigationView = findViewById(R.id.main_login_navView);
    }

    @Override
    protected void initListener() {
        imageView.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_register_login:
                        openActivity(LoginActivity.class);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_login_title_ImageView:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
    }
}