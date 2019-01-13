package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class YourCommentActivity extends BaseActivity {
    private ArrayList<Comment> myCommentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentsAdapter commentsadapter = new CommentsAdapter(this,myCommentList);
        recyclerView.setAdapter(commentsadapter);
    }
    @Override
    protected void initData(){
//        Bundle bundle = this.getIntent().getExtras();
//        if(bundle != null) {
//            myCommentList = (ArrayList<Comment>) bundle.getSerializable("commentsList");
//        }
        Comment comment = new Comment("Han Fujun","Fuck");
        myCommentList.add(comment);
        myCommentList.add(comment);
        myCommentList.add(comment);
        myCommentList.add(comment);
        //test


    }

    @Override
    protected void initView(){
        setContentView(R.layout.your_comment_list);
        recyclerView = findViewById(R.id.comments_list_RecyclerView);
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
                overridePendingTransition(R.anim.push_in_from_left, R.anim.push_out_to_right);
                break;
            default:
                break;
        }
    }

}
