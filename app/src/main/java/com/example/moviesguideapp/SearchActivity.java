/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:SearchActivity
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * version:1.12
 * author:
 * className:SearchActivity
 * date:2019/1/10 15:19
 */

public class SearchActivity extends BaseActivity {

    private ArrayList<Movie> MoviesDetails = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private RecyclerView recyclerView;
    private TextView textView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this, MoviesDetails);
        recyclerView.setAdapter(movieadapter);
    }

    protected void initData() {
        Movie movie1 = new Movie("The Shawshank Redemption", R.drawable.m01);
        MoviesDetails.add(movie1);
        Movie movie2 = new Movie("Forrest Gump", R.drawable.m01);
        MoviesDetails.add(movie2);
        Movie movie3 = new Movie("Schindler's List", R.drawable.m01);
        MoviesDetails.add(movie3);
        Movie movie4 = new Movie("Hachi: A Dog's Tale", R.drawable.m01);
        MoviesDetails.add(movie4);
        Movie movie5 = new Movie("La leggenda del pianista sull'oceano", R.drawable.m01);
        MoviesDetails.add(movie5);
        Movie movie6 = new Movie("Inception", R.drawable.m01);
        MoviesDetails.add(movie6);
        Movie movie7 = new Movie("Inception", R.drawable.m01);
        MoviesDetails.add(movie7);
        Movie movie8 = new Movie("Inception", R.drawable.m01);
        MoviesDetails.add(movie8);
    }

    protected void initView() {
        setContentView(R.layout.search);
        recyclerView = findViewById(R.id.movies_list_RecyclerView);
        textView = findViewById(R.id.search_title_back);
        searchView = findViewById(R.id.searchView);
        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
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
                overridePendingTransition(R.anim.not_move, R.anim.push_out_to_top);
                break;
            default:
                break;
        }
    }
}
