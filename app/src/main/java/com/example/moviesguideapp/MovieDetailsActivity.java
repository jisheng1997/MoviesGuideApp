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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private String path = "http://192.168.1.101:8080/MoviesGuideApp/movie_operation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        sendRequest("id_movie=" + movie_id + "&get_movie_detail=", path);
        while (curResponse == null) {
            curResponse = getResponse();
        }
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
                onBack();
                break;
            default:
                break;
        }
    }

    protected void parseJSONWithJSON(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            curMovie.setName(jsonObject.getString("name"));
            curMovie.setId_movie(Integer.parseInt(jsonObject.getString("id_movie")));
            curMovie.setMovie_pic(this.getResources().getIdentifier(jsonObject.getString("movie_pic"), "drawable", getPackageName()));
            curMovie.setMovie_type(jsonObject.getString("movie_type"));
            curMovie.setDirector(jsonObject.getString("director"));
            curMovie.setDescription(jsonObject.getString("description"));
            curMovie.setRating_douban(Float.parseFloat(jsonObject.getString("rating_douban")));
            curMovie.setRating_IMDB(Float.parseFloat(jsonObject.getString("rating_IMDB")));
            curMovie.setYear(jsonObject.getInt("movie_year"));
            curMovie.setStaring(jsonObject.getString("staring"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
