package com.example.moviesguideapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

public class MainLoginActivity extends BaseActivity {

    private ArrayList<Movie> MoviesDetails = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private Bundle bundle = new Bundle();
    private RecyclerView recyclerView;
    private ImageView back;
    private NavigationView navigationView;
    public static final String TAG="MainLoginActivity";
    private String response = null;
    private ImageView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter movieadapter = new MovieAdapter(this,MoviesDetails);
        recyclerView.setAdapter(movieadapter);
    }

    @Override
    protected void initData() {
        Movie movie1 = new Movie("The Shawshank Redemption", R.drawable.m01);
        MoviesDetails.add(movie1);
        Movie movie2 = new Movie("Forrest Gump", R.drawable.m01);
        MoviesDetails.add(movie2);
        Movie movie3 = new Movie("Schindler's List", R.drawable.m01);
        MoviesDetails.add(movie3);
        Movie movie4 = new Movie("Hachi: A Dog's Tale", R.drawable.m01);
        MoviesDetails.add(movie4);
        Movie movie5 = new Movie("La leggenda del pianista sull'oceano", R.drawable.m01);
        MoviesDetails.add(movie5);
        Movie movie6 = new Movie("Inception", R.drawable.m01);
        MoviesDetails.add(movie6);
        Movie movie7 = new Movie("Inception", R.drawable.m01);
        MoviesDetails.add(movie7);
        Movie movie8 = new Movie("Inception", R.drawable.m01);
        MoviesDetails.add(movie8);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.main_login);
        recyclerView = findViewById(R.id.movies_list_RecyclerView);
        mDrawerLayout = findViewById(R.id.main_login_DrawerLayout);
        back = findViewById(R.id.main_login_title_ImageView);
        navigationView = findViewById(R.id.main_login_navView);
        searchView = findViewById(R.id.searchView);
    }

    @Override
    protected void initListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_MyFavorite:
                        bundle.putSerializable("MoviesList", MoviesDetails);
                        openActivity(FavoriteActivity.class,bundle);
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.menu_item_MyHistory:
                        bundle.putSerializable("MoviesList", MoviesDetails);
                        openActivity(HistoryActivity.class,bundle);
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        back.setOnClickListener(this);
        searchView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_login_title_ImageView:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.searchView:
                openActivity(SearchActivity.class);
            default:
                break;
        }
    }
}

