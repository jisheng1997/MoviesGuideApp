package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class SearchActivity extends BaseActivity {

    private ArrayList<Movie> MoviesDetails = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private RecyclerView recyclerView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this, MoviesDetails);
        recyclerView.setAdapter(movieadapter);
    }

    protected void initData() {
        Movie movie1 = new Movie("肖申克的救赎 The Shawshank Redemption", R.drawable.moren);
        MoviesDetails.add(movie1);
    }

    protected void initView() {
        setContentView(R.layout.search);
        recyclerView = findViewById(R.id.movies_list);
        textView = findViewById(R.id.search_title_back);
    }

    @Override
    protected void initListener(){
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.search_title_back:
                onBack();
                break;
            default:
                break;
        }
    }
}
