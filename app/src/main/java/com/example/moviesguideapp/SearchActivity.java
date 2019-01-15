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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * version:1.12
 * className:SearchActivity
 * date:2019/1/10 15:19
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener{
    /**
     * The constant TAG.
     */
    public static final String TAG = "SearchActivity";
    private ArrayList<Movie> moviesList = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private RecyclerView recyclerView;
    private TextView textView;
    private SearchView searchView;
    private String curResponse = null;
    private MovieAdapter movieAdapter;
    /**
     * override the onCreate function
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivityManager(this);

    }
    /**
     * override the initData function
     * initialize the Data
     */
    protected void initData() {
    }
    /**
     * override the initView function
     * initialize the Views
     */
    protected void initView() {
        setContentView(R.layout.search);
        recyclerView = findViewById(R.id.movies_list_RecyclerView);
        textView = findViewById(R.id.search_title_back);
        searchView = findViewById(R.id.searchView);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search movies");
        searchView.onActionViewExpanded();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(this, moviesList);
        recyclerView.setAdapter(movieAdapter);
    }
    /**
     * override the initListener function
     * initial the listener
     */
    @Override
    protected void initListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit (String query){
                sendRequest("keyword=" + query + "&search=",movie_operation_path);
                getResult();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        textView.setOnClickListener(this);
    }
    /**
     * override the onClick function
     * initial the OnClickListener
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_title_back:
                activityManager.removeCurrent();
                overridePendingTransition(R.anim.not_move, R.anim.push_out_to_top);
                break;
            default:
                break;
        }
    }
    /**
     * override the parseJSONWithJSON function
     * handle the response from database
     */
    @Override
    protected void parseJSONWithJSON(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                moviesList.add(i, new Movie());
                moviesList.get(i).setName(jsonObject.getString("name"));
                moviesList.get(i).setId_movie(Integer.parseInt(jsonObject.getString("id_movie")));
                moviesList.get(i).setMovie_pic(this.getResources().getIdentifier(jsonObject.getString("movie_pic"), "drawable", getPackageName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * get the result from database
     */
    public void getResult(){
        while(curResponse == null){
            curResponse = getResponse();
        }
        if(!(curResponse == "")) {
            parseJSONWithJSON(curResponse);
        }else {
            Toast.makeText(this, "Do not find this movie", Toast.LENGTH_LONG).show();
        }
        movieAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(movieAdapter);
    }
}
