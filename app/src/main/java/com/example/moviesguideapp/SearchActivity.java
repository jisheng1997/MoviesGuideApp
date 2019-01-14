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
import android.util.Log;
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
    /**
     * The constant TAG.
     */
    public static final String TAG = "SearchActivity";
    private ArrayList<Movie> MoviesDetails = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private RecyclerView recyclerView;
    private TextView textView;
    private SearchView searchView;
    private String curResponse = null;
    private String keyword = null;
    private String path = "http://192.168.0.139:8081/MoviesGuideApp/movie_operation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivityManager(this);

    }

    protected void initData() {
        sendRequest("keyword=" + keyword + "&search=",path);
//        Log.d(TAG, "initData: curResponse = " + curResponse);
    }

    protected void initView() {
        setContentView(R.layout.search);
        recyclerView = findViewById(R.id.movies_list_RecyclerView);
        textView = findViewById(R.id.search_title_back);
        searchView = findViewById(R.id.searchView);
        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this, MoviesDetails);
        recyclerView.setAdapter(movieadapter);
    }

    @Override
    protected void initListener(){
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.search_title_back:
                activityManager.removeCurrent();
                overridePendingTransition(R.anim.push_in_from_bottom, R.anim.push_out_to_top);
                break;
            default:
                break;
        }
    }
}
