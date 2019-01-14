/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:HistoryActivity
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * version:1.12
 * author:
 * className:HistoryActivity
 * date:2019/1/10 15:19
 */

public class HistoryActivity extends BaseActivity {
    /**
     * The constant TAG.
     */
    public static final String TAG = "HistoryActivity";
    private ArrayList<Movie> moviesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView imageView;
    private String curResponse = null;
    private String data = null;
    private int id_user = 0;
    private String path="http://192.168.0.139:8081/MoviesGuideApp/movie_operation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivityManager(this);
    }

    @Override
    protected void initData(){
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            id_user=bundle.getInt("id_user");
        }
        data = "id_user=" + id_user+"&for_history="+"&user_management=";
        sendRequest(data,path);
        while(curResponse == null){
            curResponse = getResponse();
        }
        if(!(curResponse == "")){
            parseJSONWithJSON(curResponse);
        }else{
            Toast.makeText(this, "Your have see any movies", Toast.LENGTH_LONG).show();
        }
        Log.d(TAG, "initData: curResponse = " + curResponse);

    }

    @Override
    protected void initView(){
        setContentView(R.layout.history_list);
        recyclerView = findViewById(R.id.movies_list_RecyclerView);
        imageView = findViewById(R.id.back);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this,moviesList);
        recyclerView.setAdapter(movieadapter);
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
}
