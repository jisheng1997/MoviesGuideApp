/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:FavoriteActivity
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * version:1.12
 * author:
 * className:FavoriteActivity
 * date:2019/1/10 15:19
 */

public class FavoriteActivity extends BaseActivity {

    private ArrayList <Movie> MovieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this,MovieList);
        recyclerView.setAdapter(movieadapter);
    }

    @Override
    protected void initData(){
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            MovieList = (ArrayList<Movie>) bundle.getSerializable("moviesList");
        }
    }

    @Override
    protected void initView(){
        setContentView(R.layout.favorite_list);
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
                overridePendingTransition(R.anim.push_in_from_left, R.anim.push_out_to_right);
                break;
            default:
                break;
        }
    }


}
