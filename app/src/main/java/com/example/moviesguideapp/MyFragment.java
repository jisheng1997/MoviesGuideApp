package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static com.example.moviesguideapp.MainLoginActivity.TAG;

public class MyFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Movie> mMovieList = new ArrayList<>();
    MovieAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_listing_movies, container, false);
        mRecyclerView = view.findViewById(R.id.movies_list_RecyclerView);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mMovieList = (ArrayList<Movie>) bundle.getSerializable("moviesList");
        }
            return view;

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new MovieAdapter(this.getActivity(),mMovieList);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
