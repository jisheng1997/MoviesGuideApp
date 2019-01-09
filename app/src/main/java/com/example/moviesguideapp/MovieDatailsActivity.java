package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MovieDatailsActivity extends AppCompatActivity {

    private List<Comment> CommentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);
        initComments();
        RecyclerView recyclerView = findViewById(R.id.movie_details_RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentsAdapter adapter = new CommentsAdapter(this,CommentList);
        recyclerView.setAdapter(adapter);
    }

    private void initComments(){
        Comment comment1 = new Comment("1","good");
        CommentList.add(comment1);
        Comment comment2 = new Comment("1","good");
        CommentList.add(comment2);
        Comment comment3 = new Comment("1","good");
        CommentList.add(comment3);
        CommentList.add(comment3);
        CommentList.add(comment3);
        CommentList.add(comment3);
        CommentList.add(comment3);
        CommentList.add(comment3);
        CommentList.add(comment3);
        CommentList.add(comment3);

    }
}
