/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:MainLogoutActivity
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * version:1.12
 * author:
 * className:MainLogoutActivity
 * date:2019/1/10 15:19
 */
public class MainLogoutActivity extends BaseActivity {

    /**
     * The constant TAG.
     */
    public static final String TAG = "MainLogoutActivity";
    //nine fragment for display movieList
    private MyFragment RecommendFragment = new MyFragment();
    private MyFragment ActionFragment = new MyFragment();
    private MyFragment AdventureFragment = new MyFragment();
    private MyFragment RomanceFragment = new MyFragment();
    private MyFragment ComedyFragment = new MyFragment();
    private MyFragment CrimeFragment = new MyFragment();
    private MyFragment TopFragment = new MyFragment();

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private ArrayList<Movie> moviesList = new ArrayList<>();
    private ArrayList<Movie> recommendMoviesList = new ArrayList<>();
    private ArrayList<Movie> actionMovieList = new ArrayList<>();
    private ArrayList<Movie> adventureMovieList = new ArrayList<>();
    private ArrayList<Movie> romanceMovieList = new ArrayList<>();
    private ArrayList<Movie> comedyMovieList = new ArrayList<>();
    private ArrayList<Movie> crimeMovieList = new ArrayList<>();
    private ArrayList<Movie> topRatesMovieList = new ArrayList<>();

    private Bundle bundle = new Bundle();
    private Bundle recommendBundle = new Bundle();
    private Bundle actionBundle = new Bundle();
    private Bundle adventureBundle = new Bundle();
    private Bundle romanceBundle = new Bundle();
    private Bundle comedyBundle = new Bundle();
    private Bundle crimeBundle = new Bundle();
    private Bundle topratesBundle = new Bundle();
    private String curResponse = null;

    private ViewPager myviewpager;
    private DrawerLayout mDrawerLayout;
    private TextView tv_first;
    private TextView tv_second;
    private TextView tv_third;
    private TextView tv_fourth;
    private TextView tv_fifth;
    private TextView tv_sixth;
    private TextView tv_seventh;
    private ImageView imageView;
    private ImageView searchView;
    private NavigationView navigationView;
    private HorizontalScrollView horizontalScrollView;

    /**
     * onCreate function
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivityManager(this);
    }

    /**
     * override iniData function
     * initialize the data
     */
    @Override
    protected void initData() {

        sendRequest("get_all_movies=",movie_operation_path);
        while (curResponse == null) {
            curResponse = getResponse();
        }
        parseJSONWithJSON(curResponse);
        setRecommendMoviesList();
        fragments.add(RecommendFragment);
        actionMovieList = setMovieList("action");
        actionBundle.putSerializable("moviesList", actionMovieList);
        ActionFragment.setArguments(actionBundle);
        fragments.add(ActionFragment);
        adventureMovieList = setMovieList("adventure");
        adventureBundle.putSerializable("moviesList", adventureMovieList);
        AdventureFragment.setArguments(adventureBundle);
        fragments.add(AdventureFragment);
        romanceMovieList = setMovieList("romance");
        romanceBundle.putSerializable("moviesList", romanceMovieList);
        RomanceFragment.setArguments(romanceBundle);
        fragments.add(RomanceFragment);
        comedyMovieList = setMovieList("comedy");
        comedyBundle.putSerializable("moviesList", comedyMovieList);
        ComedyFragment.setArguments(comedyBundle);
        fragments.add(ComedyFragment);
        crimeMovieList = setMovieList("crime");
        crimeBundle.putSerializable("moviesList", crimeMovieList);
        CrimeFragment.setArguments(crimeBundle);
        fragments.add(CrimeFragment);
        setTopRatesMovieList();
        topratesBundle.putSerializable("moviesList", topRatesMovieList);
        TopFragment.setArguments(topratesBundle);
        fragments.add(TopFragment);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        myviewpager.setAdapter(adapter);
    }

    /**
     * override the iniView function
     * initialize the views
     */
    @Override
    protected void initView() {
        setContentView(R.layout.main_logout);
        myviewpager = findViewById(R.id.myviewpager);
        mDrawerLayout = findViewById(R.id.main_login_DrawerLayout);
        imageView = findViewById(R.id.main_login_title_ImageView);
        navigationView = findViewById(R.id.main_login_navView);
        searchView = findViewById(R.id.searchView);
        horizontalScrollView = findViewById(R.id.main_login_HorizontalScrollView);

        tv_first = findViewById(R.id.tv_first);
        tv_second = findViewById(R.id.tv_second);
        tv_third = findViewById(R.id.tv_third);
        tv_fourth = findViewById(R.id.tv_fourth);
        tv_fifth = findViewById(R.id.tv_fifth);
        tv_sixth = findViewById(R.id.tv_sixth);
        tv_seventh = findViewById(R.id.tv_seventh);
        TextView textArray[] = new TextView[]{tv_first, tv_second, tv_third, tv_fourth, tv_fifth, tv_sixth, tv_seventh};
    }

    /**
     * override the initListener function
     * initialize the listeners
     */
    @Override
    protected void initListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_register_login:
                        openActivity(LoginActivity.class);
                        overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left);
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return false;

            }
        });

        myviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                resetTextView();
                changeTextView(position);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
        });
        imageView.setOnClickListener(this);
        searchView.setOnClickListener(this);
        tv_first.setOnClickListener(this);
        tv_second.setOnClickListener(this);
        tv_third.setOnClickListener(this);
        tv_fourth.setOnClickListener(this);
        tv_fifth.setOnClickListener(this);
        tv_sixth.setOnClickListener(this);
        tv_seventh.setOnClickListener(this);
    }

    /**
     * override the onClick function
     * @param view
     */
    @Override
    public void onClick(View view) {
        resetTextView();
        switch (view.getId()) {
            case R.id.main_login_title_ImageView:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.searchView:
                openActivity(SearchActivity.class);
                overridePendingTransition(R.anim.push_in_from_top,R.anim.not_move);
                break;
            case R.id.tv_first:
                changeTextView(0);
                myviewpager.setCurrentItem(0);
                break;
            case R.id.tv_second:
                changeTextView(1);
                myviewpager.setCurrentItem(1);
                break;
            case R.id.tv_third:
                changeTextView(2);
                myviewpager.setCurrentItem(2);
                break;
            case R.id.tv_fourth:
                changeTextView(3);
                myviewpager.setCurrentItem(3);
                break;
            case R.id.tv_fifth:
                changeTextView(4);
                myviewpager.setCurrentItem(4);
                break;
            case R.id.tv_sixth:
                changeTextView(5);
                myviewpager.setCurrentItem(5);
                break;
            case R.id.tv_seventh:
                changeTextView(6);
                myviewpager.setCurrentItem(6);
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
                moviesList.get(i).setMovie_type(jsonObject.getString("movie_type"));
                moviesList.get(i).setRating_douban(Float.parseFloat(jsonObject.getString("rating_douban")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Reset all textviews before changes
     */
    public void resetTextView() {
        tv_first.getPaint().setFlags(0);
        tv_first.setTextColor(Color.parseColor("#A9A9A9"));
        tv_second.getPaint().setFlags(0);
        tv_second.setTextColor(Color.parseColor("#A9A9A9"));
        tv_third.getPaint().setFlags(0);
        tv_third.setTextColor(Color.parseColor("#A9A9A9"));
        tv_fourth.getPaint().setFlags(0);
        tv_fourth.setTextColor(Color.parseColor("#A9A9A9"));
        tv_fifth.getPaint().setFlags(0);
        tv_fifth.setTextColor(Color.parseColor("#A9A9A9"));
        tv_sixth.getPaint().setFlags(0);
        tv_sixth.setTextColor(Color.parseColor("#A9A9A9"));
        tv_seventh.getPaint().setFlags(0);
        tv_seventh.setTextColor(Color.parseColor("#A9A9A9"));
    }

    /**
     * Change text view which user click on
     * @param position
     */
    public void changeTextView(int position) {
        TextView textArray[] = new TextView[]{tv_first, tv_second, tv_third, tv_fourth, tv_fifth, tv_sixth, tv_seventh};
        TextView curTextView = textArray[position];
        curTextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        curTextView.setTextColor(Color.parseColor("#ffffff"));
        WindowManager wm1 = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int rb_px = (int) curTextView.getX() + curTextView.getWidth() / 2;
        horizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
    }
    /**
     * set the recommendMovieList from the movie list
     */
    public void setRecommendMoviesList() {
        for (int i = 0; i < 15; i++) {
            recommendMoviesList.add(moviesList.get(i));
        }
        recommendBundle.putSerializable("moviesList", recommendMoviesList);
        RecommendFragment.setArguments(recommendBundle);
    }
    /**
     * set the different type of movies from the movie list
     */
    public ArrayList<Movie> setMovieList(String movieType) {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        for (int i = 0; i < moviesList.size(); i++) {
            String movie_type = moviesList.get(i).getMovietype();
            String type[] = movie_type.split("/");
            for (int j = 0; j < type.length; j++) {
                if (type[j].equals(movieType)) {
                    movieArrayList.add(moviesList.get(i));
                }
            }
        }
        return movieArrayList;
    }
    /**
     * set the topRatesMovieList from the movie list
     */
    public void setTopRatesMovieList(){
        Movie tempMovie;
        for (int i =0;i<moviesList.size()-1;i++){
            for(int j=0 ; j<moviesList.size()-1-i;j++){
                if (moviesList.get(j).getRating_douban() <= moviesList.get(j+1).getRating_douban()){
                    tempMovie = moviesList.get(j);
                    moviesList.set(j,moviesList.get(j+1));
                    moviesList.set(j+1,tempMovie);
                }
            }
        }
        for (int k= 0;k<9;k++){
            topRatesMovieList.add(k,moviesList.get(k));
        }
    }

}