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
import android.widget.TextView;
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
    private TextView sort;
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
    @Override
    protected void initData(){
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            id_user=bundle.getInt("id_user");
        }
        data = "id_user=" + id_user+"&for_history="+"&user_management=";
        sendRequest(data,movie_operation_path);
        while(curResponse == null){
            curResponse = getResponse();
        }
        if(!(curResponse == "")){
            parseJSONWithJSON(curResponse);
        }else{
            Toast.makeText(this, "Your have not see any movies", Toast.LENGTH_LONG).show();
        }
        Log.d(TAG, "initData: curResponse = " + curResponse);

    }
    /**
     * override the initView function
     * initialize the Views
     */
    @Override
    protected void initView(){
        setContentView(R.layout.history_list);
        recyclerView = findViewById(R.id.movies_list_RecyclerView);
        imageView = findViewById(R.id.back);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this,moviesList);
        recyclerView.setAdapter(movieadapter);
        sort=findViewById(R.id.favoriteView);
        sort.setText("Your History");
    }
    /**
     * override the initListener function
     * initial the listener
     */
    @Override
    protected void initListener(){
        imageView.setOnClickListener(this);
    }
    /**
     * override the onClick function
     * initial the OnClickListener
     */
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
}
