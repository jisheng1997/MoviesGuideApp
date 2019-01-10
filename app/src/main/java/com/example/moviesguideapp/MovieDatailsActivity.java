package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieDatailsActivity extends BaseActivity {

    private List<Comment> CommentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView imageView;
    private Movie curMovie;
    private TextView movieName;
    private ImageView movieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentsAdapter adapter = new CommentsAdapter(this,CommentList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData(){
        Bundle bundle = this.getIntent().getExtras();
        curMovie = (Movie)bundle.getSerializable("Movie");
        movieName.setText(curMovie.getName());
        Comment comment1 = new Comment("1","good");
        CommentList.add(comment1);
        Comment comment2 = new Comment("1","good");
        CommentList.add(comment2);
        Comment comment3 = new Comment("1","good");
        CommentList.add(comment3);
    }

    @Override
    protected void initView(){
        setContentView(R.layout.movie_info);
        recyclerView = findViewById(R.id.movie_details_RecyclerView);
        imageView = findViewById(R.id.movie_info_title_ImageView);
        movieImage = findViewById(R.id.movie_imageView);
        movieName = findViewById(R.id.movies_name);
    }

    @Override
    protected void initListener(){
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.movie_info_title_ImageView:
                onBack();
                break;
            default:
                break;
        }
    }
}
