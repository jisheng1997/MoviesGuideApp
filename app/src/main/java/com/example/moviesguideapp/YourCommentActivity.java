package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class YourCommentActivity extends BaseActivity {
    /**
     * The constant TAG.
     */
    public static final String TAG = "YourCommentActivity";
    private ArrayList<Comment> myCommentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView imageView;
    private String curResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivityManager(this);

    }
    @Override
    protected void initData(){
//        Bundle bundle = this.getIntent().getExtras();
//        if(bundle != null) {
//            myCommentList = (ArrayList<Comment>) bundle.getSerializable("commentsList");
//        }
        Log.d(TAG, "initData: curResponse = " + curResponse);

    }

    @Override
    protected void initView(){
        setContentView(R.layout.your_comment_list);
        recyclerView = findViewById(R.id.comments_list_RecyclerView);
        imageView = findViewById(R.id.back);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentsAdapter commentsadapter = new CommentsAdapter(this,myCommentList);
        recyclerView.setAdapter(commentsadapter);
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
