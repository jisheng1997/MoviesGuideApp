package com.example.moviesgudieapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

public class MainLoginActivity extends BaseActivity {

    private ArrayList<Movie> MoviesDetails = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private Bundle bundle = new Bundle();
    private RecyclerView recyclerView;
    private ImageView imageView;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        initView();
        initData();
        initListener();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this, MoviesDetails);
        recyclerView.setAdapter(movieadapter);
    }

    @Override
    protected void initData() {
        Movie movie1 = new Movie("肖申克的救赎 The Shawshank Redemption", R.drawable.moren);
        MoviesDetails.add(movie1);
        Movie movie2 = new Movie("阿甘正传 Forrest Gump", R.drawable.moren);
        MoviesDetails.add(movie2);
        Movie movie3 = new Movie("辛德勒的名单 Schindler's List", R.drawable.moren);
        MoviesDetails.add(movie3);
        Movie movie4 = new Movie("忠犬八公的故事 Hachi: A Dog's Tale", R.drawable.moren);
        MoviesDetails.add(movie4);
        Movie movie5 = new Movie("海上钢琴师 La leggenda del pianista sull'oceano", R.drawable.moren);
        MoviesDetails.add(movie5);
        Movie movie6 = new Movie("盗梦空间 Inception", R.drawable.moren);
        MoviesDetails.add(movie6);
        Movie movie7 = new Movie("盗梦空间 Inception", R.drawable.moren);
        MoviesDetails.add(movie7);
        Movie movie8 = new Movie("盗梦空间 Inception", R.drawable.moren);
        MoviesDetails.add(movie8);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.main_login);
        recyclerView = findViewById(R.id.movies_list);
        mDrawerLayout = findViewById(R.id.main_login_DrawerLayout);
        imageView = findViewById(R.id.main_login_title_ImageView);
        navigationView = findViewById(R.id.main_login_navView);
    }

    @Override
    protected void initListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_MyFavorite:
                        bundle.putSerializable("MoviesList", MoviesDetails);
                        openActivity(FavoriteActivity.class,bundle);
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.menu_item_MyHistory:
                        bundle.putSerializable("MoviesList", MoviesDetails);
                        openActivity(HistoryActivity.class,bundle);
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_login_title_ImageView:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
    }
}

