/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:RecommandFragment
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * version:1.12
 * author:
 * className:RecommandFragment
 * date:2019/1/10 15:19
 */

public class RecommandFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<Movie> mMovieList = new ArrayList<>();
    MovieAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_listing_movies, container, false);
        mRecyclerView = view.findViewById(R.id.movies_list_RecyclerView);
        Bundle bundle = getArguments();
        mMovieList = (ArrayList<Movie>)bundle.getSerializable("RecommandMoviesList");
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
