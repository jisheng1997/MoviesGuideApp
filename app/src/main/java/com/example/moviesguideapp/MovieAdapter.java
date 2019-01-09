package com.example.moviesguideapp;

import android.content.Context;
import java.util.List;

public class MovieAdapter extends CommonRecycleAdapter<Movie>{
    private CommonViewHolder.onItemCommonClickListener commonClickListener;

    public MovieAdapter(Context context, List<Movie> dataList) {
        super(context, dataList, R.layout.fragment_movies);
    }

    public MovieAdapter(Context context, List<Movie> dataList, CommonViewHolder.onItemCommonClickListener commonClickListener) {
        super(context, dataList, R.layout.fragment_movies);
        this.commonClickListener = commonClickListener;
    }

    @Override
    void bindData(CommonViewHolder holder, Movie movie) {
        holder.setText(R.id.movies_name,movie.getName());
        holder.setImageResource(R.id.movies_image,movie.getImageId());
        holder.setCommonClickListener(commonClickListener);
    }

}