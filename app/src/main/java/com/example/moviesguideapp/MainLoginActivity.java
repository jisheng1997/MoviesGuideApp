/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:MainLoginActivity
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
 * className:MainLoginActivity
 */

public class MainLoginActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager myviewpager;
    private ArrayList<Movie> RecommandMovies = new ArrayList<>();
    private ArrayList<Movie> ActionMovies = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private Bundle bundle = new Bundle();
    private ImageView back;
    private NavigationView navigationView;
    public static final String TAG = "MainLoginActivity";
    private String response = null;
    private ImageView searchView;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private RecommandFragment recommandFragment = new RecommandFragment();
    private ActionFragment actionFragment = new ActionFragment();
    private TextView tv_first;
    private TextView tv_second;
    private TextView tv_third;
    private TextView tv_fourth;
    private TextView tv_fifth;
    private TextView tv_sixth;
    private HorizontalScrollView horizontalScrollView;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments.add(recommandFragment);
        fragments.add(actionFragment);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        myviewpager.setAdapter(adapter);
    }

    /**
     *
     */
    @Override
    protected void initData() {
        Movie movie1 = new Movie("Recommand", R.drawable.m01);
        RecommandMovies.add(movie1);
        RecommandMovies.add(movie1);
        RecommandMovies.add(movie1);
        RecommandMovies.add(movie1);
        RecommandMovies.add(movie1);
        RecommandMovies.add(movie1);
        bundle.putSerializable("RecommandMoviesList", RecommandMovies);
        recommandFragment.setArguments(bundle);
        Movie movie2 = new Movie("Action", R.drawable.m01);
        ActionMovies.add(movie2);
        ActionMovies.add(movie2);
        ActionMovies.add(movie2);
        ActionMovies.add(movie2);
        ActionMovies.add(movie2);
        ActionMovies.add(movie2);
        bundle.putSerializable("ActionMoviesList", ActionMovies);
        actionFragment.setArguments(bundle);
    }

    /**
     *
     */
    @Override
    protected void initView() {
        setContentView(R.layout.main_login);
        myviewpager = findViewById(R.id.myviewpager);
        mDrawerLayout = findViewById(R.id.main_login_DrawerLayout);
        back = findViewById(R.id.main_login_title_ImageView);
        navigationView = findViewById(R.id.main_login_navView);
        searchView = findViewById(R.id.searchView);

        tv_first = findViewById(R.id.tv_first);
        tv_second = findViewById(R.id.tv_second);
        tv_third = findViewById(R.id.tv_third);
        tv_fourth = findViewById(R.id.tv_fourth);
        tv_fifth = findViewById(R.id.tv_fifth);
        tv_sixth = findViewById(R.id.tv_sixth);
        horizontalScrollView = findViewById(R.id.main_login_HorizontalScrollView);



    }

    /**
     *
     */
    @Override
    protected void initListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_MyFavorite:
                        bundle.putSerializable("MoviesList", RecommandMovies);
                        openActivity(FavoriteActivity.class, bundle);
                        overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.menu_item_MyHistory:
                        bundle.putSerializable("MoviesList", ActionMovies);
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
        back.setOnClickListener(this);
        searchView.setOnClickListener(this);
        tv_first.setOnClickListener(this);
        tv_second.setOnClickListener(this);
        tv_third.setOnClickListener(this);
        tv_fourth.setOnClickListener(this);
        tv_fifth.setOnClickListener(this);
        tv_sixth.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_login_title_ImageView:
                mDrawerLayout.openDrawer(GravityCompat.START);
                overridePendingTransition(R.anim.push_in_from_top, R.anim.push_out_to_bottom);
                break;
            case R.id.searchView:
                openActivity(SearchActivity.class);
                break;
            case R.id.tv_first:
                changeTextView(tv_first);
                myviewpager.setCurrentItem(0);
                showLongToast("Recommand");
                break;
            case R.id.tv_second:
                changeTextView(tv_second);
                myviewpager.setCurrentItem(1);
                showLongToast("Action");
                break;
            case R.id.tv_third:
                changeTextView(tv_third);
                myviewpager.setCurrentItem(2);
                showLongToast("Adventure");
                break;
            case R.id.tv_fourth:
                changeTextView(tv_fourth);
                myviewpager.setCurrentItem(3);
                showLongToast("Sci-fi");
                break;
            case R.id.tv_fifth:
                changeTextView(tv_fifth);
                myviewpager.setCurrentItem(4);
                showLongToast("Drama");
                break;
            case R.id.tv_sixth:
                changeTextView(tv_sixth);
                myviewpager.setCurrentItem(5);
                showLongToast("Romance");
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
    }

    public void resetTextView(){

    }

    public void changeTextView(TextView textView){
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView.setTextColor(Color.parseColor("#ff0000"));
        WindowManager wm1 = this.getWindowManager();
        int screenWidth = wm1.getDefaultDisplay().getWidth();
        int rb_px = (int)textView.getX() + textView.getWidth() / 2;
        horizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
    }
}



