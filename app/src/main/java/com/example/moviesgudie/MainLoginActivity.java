package com.example.moviesgudie;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

public class MainLoginActivity extends BaseActivity{

    private List<Movie> MovieList = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        initMovies();
        RecyclerView recyclerView = findViewById(R.id.movies_list);
        Toolbar toolbar = findViewById(R.id.main_login_title_Toorbar);
        mDrawerLayout = findViewById(R.id.main_login_DrawerLayout);
        ImageView imageView = findViewById(R.id.main_login_title_ImageView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this,MovieList);
        recyclerView.setAdapter(movieadapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void initMovies(){
       Movie movie1 = new Movie("肖申克的救赎 The Shawshank Redemption",R.drawable.moren);
       MovieList.add(movie1);
       Movie movie2 = new Movie("阿甘正传 Forrest Gump",R.drawable.moren);
       MovieList.add(movie2);
       Movie movie3 = new Movie("辛德勒的名单 Schindler's List",R.drawable.moren);
       MovieList.add(movie3);
       Movie movie4 = new Movie("忠犬八公的故事 Hachi: A Dog's Tale",R.drawable.moren);
       MovieList.add(movie4);
       Movie movie5 = new Movie("海上钢琴师 La leggenda del pianista sull'oceano",R.drawable.moren);
       MovieList.add(movie5);
       Movie movie6 = new Movie("盗梦空间 Inception",R.drawable.moren);
       MovieList.add(movie6);
    }

}
