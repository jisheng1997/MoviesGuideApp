package com.example.moviesgudieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    ArrayList <Movie> MovieList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_list);
        getMoviesData(this.getIntent().getExtras());
        RecyclerView recyclerView = findViewById(R.id.movies_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this,MovieList);
        recyclerView.setAdapter(movieadapter);
    }

    private void getMoviesData(Bundle bundle){
        MovieList = (ArrayList<Movie>)bundle.getSerializable("MoviesList");
    }
}
