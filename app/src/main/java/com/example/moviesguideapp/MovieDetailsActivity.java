/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:MovieDetailsActivity
 * date:2019/1/10 15:19
 */
package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * version:1.12
 * author:
 * className:MovieDetailsActivity
 * date:2019/1/10 15:19
 */

public class MovieDetailsActivity extends BaseActivity {

    private ArrayList<Comment> CommentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView imageView;
    private Movie curMovie;
    private TextView movieName;
    private ImageView movieImage;
    private TextView movieDirector;
    private TextView movieStaring;
    private TextView movieRating;
    private TextView movieYear;
    private TextView movieDescription;
    private int movie_id;
    private String curResponse = null;
    private String path = "http://192.168.0.139:8081/MoviesGuideApp/movie_operation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivityManager(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentsAdapter adapter = new CommentsAdapter(this, CommentList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        Bundle bundle = this.getIntent().getExtras();
        curMovie = (Movie) bundle.getSerializable("Movie");
        movie_id = curMovie.getId_movie();
        Log.d(TAG, "initData: curMovie = " + movie_id);
        sendRequest("id_movie=" + movie_id + "&get_movie_detail=", path);
        while (curResponse == null) {
            curResponse = getResponse();
        }
        Log.d(TAG, "initData: curResponse = " + curResponse);
        parseJSONWithJSON(curResponse);
        setMovieDetail();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.movie_info);
        recyclerView = findViewById(R.id.movie_details_RecyclerView);
        imageView = findViewById(R.id.movie_info_title_ImageView);
        movieImage = findViewById(R.id.movie_imageView);
        movieName = findViewById(R.id.movies_name);
        movieDirector = findViewById(R.id.movies_director);
        movieStaring = findViewById(R.id.movies_staring);
        movieRating = findViewById(R.id.movies_rating);
        movieYear = findViewById(R.id.movies_year);
        movieDescription = findViewById(R.id.movies_description);
    }

    public void setMovieDetail() {
        movieName.setText(curMovie.getName());
        movieImage.setImageResource(curMovie.getMovie_pic());
        movieDirector.setText("Director:" + curMovie.getDirector());
        movieStaring.setText("Staring:" + curMovie.getStaring());
        movieRating.setText("Rating" + curMovie.getRating_douban() + "/" + curMovie.getRating_IMDB());
        movieYear.setText("Year:" + curMovie.getYear());
        movieDescription.setText("Description:" + curMovie.getDescription());
    }

    @Override
    protected void initListener() {
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie_info_title_ImageView:
                activityManager.removeCurrent();
                break;
            default:
                break;
        }
    }

    protected void parseJSONWithJSON(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject movieObject = jsonArray.getJSONObject(0);
            curMovie.setName(movieObject.getString("name"));
            curMovie.setId_movie(Integer.parseInt(movieObject.getString("id_movie")));
            curMovie.setMovie_pic(this.getResources().getIdentifier(movieObject.getString("movie_pic"), "drawable", getPackageName()));
            curMovie.setMovie_type(movieObject.getString("movie_type"));
            curMovie.setDirector(movieObject.getString("director"));
            curMovie.setDescription(movieObject.getString("description"));
            curMovie.setRating_douban(Float.parseFloat(movieObject.getString("rating_douban")));
            curMovie.setRating_IMDB(Float.parseFloat(movieObject.getString("rating_IMDB")));
            curMovie.setYear(movieObject.getInt("movie_year"));
            curMovie.setStaring(movieObject.getString("staring"));
            if(jsonArray.length() > 1) {
                for (int i = 1; i < jsonArray.length(); i++) {
                    JSONObject commentObject = jsonArray.getJSONObject(i);
                    CommentList.add(i - 1, new Comment());
                    CommentList.get(i - 1).setUsername(commentObject.getString("username") + ":");
                    CommentList.get(i - 1).setComment(commentObject.getString("comment"));
                }
            }else{
                Toast.makeText(this, "No one comment this movie. Go to add your comment", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
