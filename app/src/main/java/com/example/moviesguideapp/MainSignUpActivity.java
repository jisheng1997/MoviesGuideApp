/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:MainSignUpActivity
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
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * version:1.12
 * author:
 * date:2019/1/10 15:19
 * className:MainSignUpActivity
 */
public class MainSignUpActivity extends BaseActivity {

    /**
     * The constant TAG.
     */
    public static final String TAG = "MainSignUpActivity";

    //nine fragment for display movieList
    private MyFragment RecommandFragment = new MyFragment();
    private MyFragment ActionFragment = new MyFragment();
    private MyFragment AdventureFragment = new MyFragment();
    private MyFragment ScifiFragment = new MyFragment();
    private MyFragment DramaFragment = new MyFragment();
    private MyFragment RomanceFragment = new MyFragment();
    private MyFragment ComedyFragment = new MyFragment();
    private MyFragment CrimeFragment = new MyFragment();
    private MyFragment ThrillerFragment = new MyFragment();

    private ArrayList<Movie> moviesList = new ArrayList<>();

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private Bundle bundle = new Bundle();
    private String response;

    private ViewPager myviewpager;
    private DrawerLayout mDrawerLayout;

    private TextView tv_first;
    private TextView tv_second;
    private TextView tv_third;
    private TextView tv_fourth;
    private TextView tv_fifth;
    private TextView tv_sixth;
    private TextView tv_seventh;
    private TextView tv_eighth;
    private TextView tv_ninth;
    private ImageView back;
    private ImageView searchView;
    private NavigationView navigationView;
    private HorizontalScrollView horizontalScrollView;


    /**
     * onCreate function
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        while(response==null){}
//        parseJSONWithJSON(response);
        initFragment();
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        myviewpager.setAdapter(adapter);
    }

    /**
     * override iniData function
     * initialize the data
     */
    @Override
    protected void initData() {
        /*
        test
         */
        Movie movie = new Movie("test", R.drawable.m01);
        moviesList.add(movie);
        moviesList.add(movie);
        moviesList.add(movie);
        bundle.putSerializable("moviesList", moviesList);
        RecommandFragment.setArguments(bundle);
    }

    private void initFragment() {
        fragments.add(RecommandFragment);
        fragments.add(ActionFragment);
        fragments.add(AdventureFragment);
        fragments.add(ScifiFragment);
        fragments.add(DramaFragment);
        fragments.add(RomanceFragment);
        fragments.add(ComedyFragment);
        fragments.add(CrimeFragment);
        fragments.add(ThrillerFragment);
    }

    /**
     * override the iniView function
     * initialize the views
     */
    @Override
    protected void initView() {
        setContentView(R.layout.main_signup);

        myviewpager = findViewById(R.id.myviewpager);
        mDrawerLayout = findViewById(R.id.main_signUp_DrawerLayout);
        back = findViewById(R.id.main_signUp_title_ImageView);
        navigationView = findViewById(R.id.main_signUp_navView);
        searchView = findViewById(R.id.searchView);
        horizontalScrollView = findViewById(R.id.main_signUp_HorizontalScrollView);

        tv_first = findViewById(R.id.tv_first);
        tv_second = findViewById(R.id.tv_second);
        tv_third = findViewById(R.id.tv_third);
        tv_fourth = findViewById(R.id.tv_fourth);
        tv_fifth = findViewById(R.id.tv_fifth);
        tv_sixth = findViewById(R.id.tv_sixth);
        tv_seventh = findViewById(R.id.tv_seventh);
        tv_eighth = findViewById(R.id.tv_eighth);
        tv_ninth = findViewById(R.id.tv_ninth);
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
                    case R.id.menu_item_MyFavorite:
                        openActivity(FavoriteActivity.class, bundle);
                        overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.menu_item_MyHistory:
                        openActivity(HistoryActivity.class, bundle);
                        overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left);
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
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

        back.setOnClickListener(this);
        searchView.setOnClickListener(this);
        tv_first.setOnClickListener(this);
        tv_second.setOnClickListener(this);
        tv_third.setOnClickListener(this);
        tv_fourth.setOnClickListener(this);
        tv_fifth.setOnClickListener(this);
        tv_sixth.setOnClickListener(this);
        tv_seventh.setOnClickListener(this);
        tv_eighth.setOnClickListener(this);
        tv_ninth.setOnClickListener(this);
    }

    /**
     * override the onClick function
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        resetTextView();
        switch (view.getId()) {
            case R.id.main_signUp_title_ImageView:
                mDrawerLayout.openDrawer(GravityCompat.START);
                overridePendingTransition(R.anim.push_in_from_top, R.anim.push_out_to_bottom);
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
            case R.id.tv_eighth:
                changeTextView(7);
                myviewpager.setCurrentItem(7);
                break;
            case R.id.tv_ninth:
                changeTextView(8);
                myviewpager.setCurrentItem(8);
                break;
            default:
                break;
        }
    }

    /**
     * Reset all textviews before changes
     */
    public void resetTextView() {
        tv_first.getPaint().setFlags(0);
        tv_first.setTextColor(Color.parseColor("#000000"));
        tv_second.getPaint().setFlags(0);
        tv_second.setTextColor(Color.parseColor("#000000"));
        tv_third.getPaint().setFlags(0);
        tv_third.setTextColor(Color.parseColor("#000000"));
        tv_fourth.getPaint().setFlags(0);
        tv_fourth.setTextColor(Color.parseColor("#000000"));
        tv_fifth.getPaint().setFlags(0);
        tv_fifth.setTextColor(Color.parseColor("#000000"));
        tv_sixth.getPaint().setFlags(0);
        tv_sixth.setTextColor(Color.parseColor("#000000"));
        tv_seventh.getPaint().setFlags(0);
        tv_seventh.setTextColor(Color.parseColor("#000000"));
        tv_eighth.getPaint().setFlags(0);
        tv_eighth.setTextColor(Color.parseColor("#000000"));
        tv_ninth.getPaint().setFlags(0);
        tv_ninth.setTextColor(Color.parseColor("#000000"));
    }

    /**
     * Change text view which user click on
     *
     * @param position
     */
    public void changeTextView(int position) {
        TextView textArray[] = new TextView[]{tv_first, tv_second, tv_third, tv_fourth, tv_fifth, tv_sixth, tv_seventh, tv_eighth, tv_ninth};
        TextView curTextView = textArray[position];
        curTextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        curTextView.setTextColor(Color.parseColor("#00ffff"));
        WindowManager wm1 = this.getWindowManager();
        int screenWidth = wm1.getDefaultDisplay().getWidth();
        int rb_px = (int) curTextView.getX() + curTextView.getWidth() / 2;
        horizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
    }
}



