package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

public class HistoryActivity extends BaseActivity {
    private ArrayList<Movie> MovieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this,MovieList);
        recyclerView.setAdapter(movieadapter);
    }

    @Override
    protected void initData(){
        Bundle bundle = this.getIntent().getExtras();
        MovieList = (ArrayList<Movie>)bundle.getSerializable("MoviesList");
    }

    @Override
    protected void initView(){
        setContentView(R.layout.history_list);
        recyclerView = findViewById(R.id.movies_list_RecyclerView);
        imageView = findViewById(R.id.back);
    }

    @Override
    protected void initListener(){
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                onBack();
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            default:
                break;
        }
    }
}
