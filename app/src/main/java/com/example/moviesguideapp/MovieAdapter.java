/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:MovieAdapter
 * date:2019/1/10 15:19
 */
package com.example.moviesguideapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.moviesguideapp.MainLoginActivity.TAG;

/**
 * version:1.12
 * className:MovieAdapter
 * date:2019/1/10 15:19
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> mMovieList;
    private Activity mActivity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View movieView;
        ImageView MovieImage;
        TextView MovieName;

        public ViewHolder(View view) {
            super(view);
            movieView = view;
            MovieImage = view.findViewById(R.id.movies_image);
            MovieName = view.findViewById(R.id.movies_name);
        }
    }

    public MovieAdapter(Activity activity, List<Movie> MovieList) {
        mActivity = activity;
        mMovieList = MovieList;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        final MovieAdapter.ViewHolder holder = new MovieAdapter.ViewHolder(view);
        holder.movieView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Movie movie = mMovieList.get(position);
                Bundle bundle = mActivity.getIntent().getExtras();
                if(bundle!=null){
                    bundle.putSerializable("Movie", movie);
                    Intent intent = new Intent(mActivity, MovieDetailsActivity.class);
                    intent.putExtras(bundle);
                    mActivity.startActivity(intent);
                }else{
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("id_user",0);
                    bundle2.putSerializable("Movie", movie);
                    Intent intent = new Intent(mActivity, MovieDetailsActivity.class);
                    intent.putExtras(bundle2);
                    mActivity.startActivity(intent);
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.MovieImage.setImageResource(movie.getMovie_pic());
        holder.MovieName.setText(movie.getName());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

}
