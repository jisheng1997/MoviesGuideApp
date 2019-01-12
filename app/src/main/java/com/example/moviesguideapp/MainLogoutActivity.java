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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
/**
 * version:1.12
 * author:
 * className:MainLogoutActivity
 * date:2019/1/10 15:19
 */
public class MainLogoutActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    private ViewPager myviewpager;
    private ArrayList<Movie> RecommandMovies = new ArrayList<>();
    private ArrayList<Movie> ActionMovies = new ArrayList<>();
    private ArrayList<Movie> moviesList = new ArrayList<>();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        while(response==null){}
//        parseJSONWithJSON(response);
        fragments.add(recommandFragment);
        fragments.add(actionFragment);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        myviewpager.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        receive();
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

    @Override
    protected void initView() {
        setContentView(R.layout.main_logout);
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

    @Override
    protected void initListener() {
        back.setOnClickListener(this);
        searchView.setOnClickListener(this);
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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_login_title_ImageView:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.searchView:
                openActivity(SearchActivity.class);
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
    /*
     *  getting data from server
     */

    private void receive() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response = executeHttpGet();
                        Log.d(TAG, "run&onCreate: " + response);
                    }
                }
        ).start();
    }

    private String executeHttpGet() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path="http://172.29.4.41:8081/Android_server/get_all_movie.php";
        try {
            con = (HttpURLConnection) new URL(path).openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            if(con.getResponseCode()==200){
                Log.d(TAG, "executeHttpGet&onCreate: successful");
                in=con.getInputStream();
                //Log.d(TAG, "executeHttpGet&onCreate:"+parseInfo(in));
                return parseInfo(in);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "executeHttpGet&onCreate: wrong way");
        return null;
    }

    private String parseInfo(InputStream in) throws IOException {
        BufferedReader  br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line=null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
        }
        Log.i(TAG, "parseInfo: sb:"+sb.toString());
        return sb.toString();
    }

    /*
     *  parsing String(response) with JSONObject
     */
    private void parseJSONWithJSON(String jsonData) {
        try{
            Log.d(TAG, "parseJSONWithJSON&onCreate:"+jsonData);
            JSONArray jsonArray = new JSONArray(jsonData);
            Log.d(TAG, "parseJSONWithJSON&onCreate:"+jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObeject = jsonArray.getJSONObject(i);
                Log.d(TAG, "parseJSONWithJSON&onCreate:"+jsonObeject.getString("movie_pic"));
                moviesList.add(i,new Movie());
                moviesList.get(i).setName(jsonObeject.getString("name"));
                moviesList.get(i).setDescription(jsonObeject.getString("description"));
                moviesList.get(i).setDirector(jsonObeject.getString("director"));
                moviesList.get(i).setId_movie(Integer.parseInt(jsonObeject.getString("id_movie")));
                moviesList.get(i).setMovie_pic(this.getResources().getIdentifier(jsonObeject.getString("movie_pic"),"drawable",getPackageName()));
                moviesList.get(i).setMovie_type(jsonObeject.getString("movie_type"));
                moviesList.get(i).setRating_douban(Float.parseFloat(jsonObeject.getString("rating_douban")));
                moviesList.get(i).setRating_IMDB(Float.parseFloat(jsonObeject.getString("rating_IMDB")));
                moviesList.get(i).setStaring(jsonObeject.getString("staring"));
                moviesList.get(i).setYear(Integer.parseInt(jsonObeject.getString("year")));
            }
        } catch (Exception e){
            e.printStackTrace();
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