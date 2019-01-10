package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainLogoutActivity extends BaseActivity {

    private ArrayList<Movie> moviesList = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private Bundle bundle = new Bundle();
    private RecyclerView movies_list_recyclerView;
    private RecyclerView genre_recyclerView;
    private ImageView imageView;
    private NavigationView navigationView;
    public static final String TAG="MainLogoutActivity";
    private String response = null;
    private ImageView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: parse start");
        Log.d(TAG, "onCreate: response="+response);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        movies_list_recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this,moviesList);
        movies_list_recyclerView.setAdapter(movieadapter);

    }

    @Override
    protected void initData() {
        receive();
//        Movie movie1 = new Movie("The Shawshank Redemption", R.drawable.m01);
//        MoviesDetails.add(movie1);
//        Movie movie2 = new Movie("Forrest Gump", R.drawable.m01);
//        MoviesDetails.add(movie2);
//        Movie movie3 = new Movie("Schindler's List", R.drawable.m01);
//        MoviesDetails.add(movie3);
//        Movie movie4 = new Movie("Hachi: A Dog's Tale", R.drawable.m01);
//        MoviesDetails.add(movie4);
//        Movie movie5 = new Movie("La leggenda del pianista sull'oceano", R.drawable.m01);
//        MoviesDetails.add(movie5);
//        Movie movie6 = new Movie("Inception", R.drawable.m01);
//        MoviesDetails.add(movie6);
//        Movie movie7 = new Movie("Inception", R.drawable.m01);
//        MoviesDetails.add(movie7);
//        Movie movie8 = new Movie("Inception", R.drawable.m01);
//        MoviesDetails.add(movie8);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.main_logout);
//        genre_recyclerView = findViewById(R.id.gerne_RecyclerView);
        movies_list_recyclerView = findViewById(R.id.movies_list_RecyclerView);
        mDrawerLayout = findViewById(R.id.main_login_DrawerLayout);
        imageView = findViewById(R.id.main_login_title_ImageView);
        navigationView = findViewById(R.id.main_login_navView);
        searchView = findViewById(R.id.searchView);
    }

    @Override
    protected void initListener() {
        imageView.setOnClickListener(this);
        searchView.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_register_login:
                        openActivity(LoginActivity.class);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_login_title_ImageView:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.searchView:
                openActivity(SearchActivity.class);
            default:
                break;
        }
    }


    /*
     *  getting data from server
     */

    private void receive() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response = executeHttpGet();
                        Log.d(TAG, "run&onCreate: " + response);
                    }
                }
        ).start();
    }

    private String executeHttpGet() {
        HttpURLConnection con = null;
        InputStream in = null;
        String path="http://172.29.4.41:8081/Android_server/get_all_movie.php";
        try {
            con = (HttpURLConnection) new URL(path).openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            if(con.getResponseCode()==200){
                Log.d(TAG, "executeHttpGet&onCreate: successful");
                in=con.getInputStream();
                //Log.d(TAG, "executeHttpGet&onCreate:"+parseInfo(in));
                return parseInfo(in);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "executeHttpGet&onCreate: wrong way");
        return null;
    }

    private String parseInfo(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line=null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
        }
        Log.i(TAG, "parseInfo: sb:"+sb.toString());
        return sb.toString();
    }

    /*
     *  parsing String(response) with JSONObject
     */
    private void parseJSONWithJSON(String jsonData) {
        try{
            Log.d(TAG, "parseJSONWithJSON&onCreate:"+jsonData);
            JSONArray jsonArray = new JSONArray(jsonData);
            Log.d(TAG, "parseJSONWithJSON&onCreate:"+jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObeject = jsonArray.getJSONObject(i);
                Movie movie = new Movie(Integer.parseInt(jsonObeject.getString("id_movie")),
                                        Integer.parseInt(jsonObeject.getString("movie_pic")),
                                        jsonObeject.getString("movie_type"),
                                        jsonObeject.getString("name"),
                                        jsonObeject.getString("director"),
                                        jsonObeject.getString("staring"),
                                        Integer.parseInt(jsonObeject.getString("years")),
                                        jsonObeject.getString("description"),
                                        Float.parseFloat(jsonObeject.getString("rating_douban")),
                                        Float.parseFloat(jsonObeject.getString("rating_IMDB"))
                                );
                moviesList.set(i,movie);
                //Log.d(TAG, "parseJSONWithJSON&onCreate:"+jsonObeject.getString("name"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}