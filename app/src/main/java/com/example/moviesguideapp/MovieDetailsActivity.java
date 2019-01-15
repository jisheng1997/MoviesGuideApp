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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * version:1.12
 * className:MovieDetailsActivity
 * date:2019/1/10 15:19
 */

public class MovieDetailsActivity extends BaseActivity {

    private ArrayList<Comment> CommentList = new ArrayList<>();
    private ArrayList<Movie> favoriteList = new ArrayList<>();
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
    private ImageView doFavorite;
    private Button commentButton;
    private EditText commentEditText;
    private boolean isFavorite = false;
    private boolean haveFavorite = false;
    private int movie_id;
    private int id_user = 0;
    private String curResponse = null;
    private String commentContent = null;

    /**
     * override the onCreate function
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivityManager(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentsAdapter adapter = new CommentsAdapter(this, CommentList);
        recyclerView.setAdapter(adapter);

    }

    /**
     * override the initData function
     * initialize the Data
     */
    @Override
    protected void initData() {
        Bundle bundle = this.getIntent().getExtras();
        curMovie = (Movie) bundle.getSerializable("Movie");
        movie_id = curMovie.getId_movie();
        id_user = bundle.getInt("id_user");
//        Log.d(TAG, "initData: curMovie = " + movie_id);
        sendRequest("id_movie=" + movie_id + "&get_movie_detail=", movie_operation_path);
        while (curResponse == null) {
            curResponse = getResponse();
        }
        sendRequest("id_movie=" + movie_id + "&id_user=" + id_user + "&refresh_history=", movie_operation_path);
        while (curResponse == null) {
            curResponse = getResponse();
        }
//        Log.d(TAG, "initData: curResponse = " + curResponse);
        parseJSONWithJSON(curResponse);
        setMovieDetail();
        if (id_user != 0) {
            curResponse = null;
            sendRequest("id_user=" + id_user + "&for_favorite=" + "&user_management=", movie_operation_path);
            while (curResponse == null) {
                curResponse = getResponse();
            }
            parseJSONWithJSONFavorite(curResponse);
        }
    }

    /**
     * override the initView function
     * initialize the Views
     */
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
        doFavorite = findViewById(R.id.movie_imageFavorite);
        commentEditText = findViewById(R.id.comment_EditText);
        commentButton = findViewById(R.id.comment_Button);
    }

    /**
     * set the information of movies
     */
    public void setMovieDetail() {
        movieName.setText(curMovie.getName());
        movieImage.setImageResource(curMovie.getMovie_pic());
        movieDirector.setText("Director: " + curMovie.getDirector());
        movieStaring.setText("Staring: " + curMovie.getStaring());
        movieRating.setText("Rating douban:" + curMovie.getRating_douban() + "/IMDB: " + curMovie.getRating_IMDB());
        movieYear.setText("Year: " + curMovie.getYear());
        movieDescription.setText("Description: " + curMovie.getDescription());
    }

    /**
     * override the initListener function
     * initial the listener
     */
    @Override
    protected void initListener() {
        imageView.setOnClickListener(this);
        doFavorite.setOnClickListener(this);
        commentButton.setOnClickListener(this);
    }

    /**
     * override the onClick function
     * initial the OnClickListener
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie_info_title_ImageView:
                activityManager.removeCurrent();
                break;
            case R.id.movie_imageFavorite:
                if (id_user == 0) {
                    Toast.makeText(this, "Please login", Toast.LENGTH_LONG).show();
                } else {
                    sendRequest("id_user=" + id_user + "&id_movie=" + movie_id + "&refresh_favorite=", movie_operation_path);
                    if (haveFavorite) {
                        doFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.notfavorite));
                        haveFavorite = false;
                    } else {
                        doFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.favorite));
                        haveFavorite = true;
                    }
                }
                break;
            case R.id.comment_Button:
                makeComment();
            default:
                break;
        }
    }

    /**
     * override the parseJSONWithJSON function
     * handle the response from database
     */
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
            if (jsonArray.length() > 1) {
                for (int i = 1; i < jsonArray.length(); i++) {
                    JSONObject commentObject = jsonArray.getJSONObject(i);
                    CommentList.add(i - 1, new Comment());
                    CommentList.get(i - 1).setUsername(commentObject.getString("username") + ":");
                    CommentList.get(i - 1).setComment(commentObject.getString("comment"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * override the parseJSONWithJSONFavorite function
     * handle the response from database
     */
    protected void parseJSONWithJSONFavorite(String jsonData) {
        try {
            JSONArray jsonArrayFavorite = new JSONArray(jsonData);

            for (int i = 0; i < jsonArrayFavorite.length(); i++) {
                JSONObject jsonObject = jsonArrayFavorite.getJSONObject(i);
                favoriteList.add(i, new Movie());
                favoriteList.get(i).setId_movie(Integer.parseInt(jsonObject.getString("id_movie")));
            }
            for (int i = 0; i < favoriteList.size(); i++) {
                if (favoriteList.get(i).getId_movie() == curMovie.getId_movie()) {
                    isFavorite = true;
                }
            }
            if (isFavorite) {
                doFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.favorite));
                haveFavorite = true;
                isFavorite = false;
            } else {
                doFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.notfavorite));
                haveFavorite = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the comments from database
     */
    public void makeComment() {
        if (id_user == 0) {
            Toast.makeText(this, "Please login", Toast.LENGTH_LONG).show();
        } else {
            commentContent = commentEditText.getText().toString();
            if (TextUtils.isEmpty(commentContent)) {
                Toast.makeText(this, "comment should not be empty", Toast.LENGTH_LONG).show();
                return;
            }
            curResponse = null;
            sendRequest("id_user=" + id_user + "&id_movie=" + movie_id + "&comment_content=" + commentContent + "&make_comment=", movie_operation_path);
            while (curResponse == null) {
                curResponse = getResponse();
            }
            if (curResponse.equals("1")) {
                Toast.makeText(this, "comment make successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "You have comment before don't comment again", Toast.LENGTH_LONG).show();
                this.commentButton.setVisibility(View.INVISIBLE);
            }
            curResponse = null;
        }
    }
}
