/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:MainLoginActivity
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * version:1.12
 * author:
 * date:2019/1/10 15:19
 * className:MainLoginActivity
 */
public class MainLoginActivity extends BaseActivity {

    /**
     * The constant TAG.
     */
    public static final String TAG = "MainLoginActivity";

    //nine fragment for display movieList
    private MyFragment RecommandFragment = new MyFragment();
    private MyFragment ActionFragment = new MyFragment();
    private MyFragment AdventureFragment = new MyFragment();
    private MyFragment RomanceFragment = new MyFragment();
    private MyFragment ComedyFragment = new MyFragment();
    private MyFragment CrimeFragment = new MyFragment();

    private ArrayList<Movie> moviesList = new ArrayList<>();
    private ArrayList<Movie> recommandMoviesList = new ArrayList<>();
    private ArrayList<Movie> actionMovieList = new ArrayList<>();
    private ArrayList<Movie> adventureMovieList = new ArrayList<>();
    private ArrayList<Movie> romanceMovieList = new ArrayList<>();
    private ArrayList<Movie> comedyMovieList = new ArrayList<>();
    private ArrayList<Movie> crimeMovieList = new ArrayList<>();

    private Bundle recommandBundle = new Bundle();
    private Bundle actionBundle = new Bundle();
    private Bundle adventureBundle = new Bundle();
    private Bundle romanceBundle = new Bundle();
    private Bundle comedyBundle = new Bundle();
    private Bundle crimeBundle = new Bundle();

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private Bundle bundle = new Bundle();
    private int id_user = 0;
    private String userName = null;
    private String curResponse = null;
    private String path = "http://192.168.0.139:8081/MoviesGuideApp/movie_operation.php";

    private ViewPager myviewpager;
    private DrawerLayout mDrawerLayout;
    private View headview;
    private CircleImageView headImage;
    public static final int SELECT_PIC = 1;

    private TextView tv_first;
    private TextView tv_second;
    private TextView tv_third;
    private TextView tv_fourth;
    private TextView tv_fifth;
    private TextView tv_sixth;
    private TextView tv_seventh;
    private TextView userNameView;
    private ImageView imageView;
    private ImageView searchView;
    private NavigationView navigationView;
    private TextView drawerUserNameView;
    private HorizontalScrollView horizontalScrollView;
    private AlertDialog.Builder alertDialog;
    Dialog dia;


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
        //send request to get all movies
        sendRequest("get_all_movies=",path);
        while (curResponse == null) {
            curResponse = getResponse();
        }
        Log.d(TAG, "initData: curResponse = " + curResponse);
        //process data and initialize all the fragments
        parseJSONWithJSON(curResponse);
        setRecommandMoviesList();
        fragments.add(RecommandFragment);
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
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        myviewpager.setAdapter(adapter);

        //set user name
        Bundle bundle = this.getIntent().getExtras();
        userName = bundle.getString("username");
        id_user = bundle.getInt("id_user");
        userNameView.setText(userName);
        drawerUserNameView.setText(userName);

    }

    /**
     * override the iniView function
     * initialize the views
     */
    @Override
    protected void initView() {
        setContentView(R.layout.main_login);

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
        headview = navigationView.inflateHeaderView(R.layout.main_login_nav_header);
        headImage = headview.findViewById(R.id.main_login_nav_header_headImage);
        userNameView = findViewById(R.id.main_login_title_TextView);
        View headerView = navigationView.getHeaderView(0);
        drawerUserNameView = headerView.findViewById(R.id.main_login_nav_header_name);
        setAlertDialog();
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
                        openActivity(FavoriteActivity.class);
                        overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.menu_item_MyHistory:
                        openActivity(HistoryActivity.class);
                        overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.menu_item_signout:
                        alertDialog.show();
                        break;
                    case R.id.menu_item_help:
                        dia.show();
                        break;
                    case R.id.menu_item_MyComment:
                        openActivity(YourCommentActivity.class);
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
        imageView.setOnClickListener(this);
        searchView.setOnClickListener(this);
        tv_first.setOnClickListener(this);
        tv_second.setOnClickListener(this);
        tv_third.setOnClickListener(this);
        tv_fourth.setOnClickListener(this);
        tv_fifth.setOnClickListener(this);
        tv_sixth.setOnClickListener(this);
        tv_seventh.setOnClickListener(this);
        headImage.setOnClickListener(this);
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
            case R.id.tv_sixth:
                changeTextView(4);
                myviewpager.setCurrentItem(4);
                break;
            case R.id.tv_seventh:
                changeTextView(6);
                myviewpager.setCurrentItem(6);
                break;
            case R.id.main_login_nav_header_headImage:
                Intent intent = new Intent(this,HeadChooseActivity.class);
                startActivityForResult(intent,SELECT_PIC);
            default:
                break;
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
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm1 = this.getWindowManager();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int rb_px = (int) curTextView.getX() + curTextView.getWidth() / 2;
        horizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
    }

    /**
     *
     */
    public void setAlertDialog() {
        alertDialog = new AlertDialog.Builder(MainLoginActivity.this);
        alertDialog.setTitle("Confirm");
        alertDialog.setMessage("Are you sure to sign out?");
        alertDialog.setIcon(R.drawable.alert);
        alertDialog.create();
        alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openActivityAndCloseThis(MainLogoutActivity.class);
                overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left);

            }
        });

        dia = new Dialog(MainLoginActivity.this, R.style.edit_AlertDialog_style);
        dia.setContentView(R.layout.dialog_layout);
        ImageView dialogImageView = (ImageView) dia.findViewById(R.id.economicalHelp);
        dialogImageView.setBackgroundResource(R.drawable.code);
        dia.setCanceledOnTouchOutside(true);
        Window w = dia.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        dia.onWindowAttributesChanged(lp);
        dialogImageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dia.dismiss();
                    }
                });
    }

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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRecommandMoviesList() {
        for (int i = 0; i < 9; i++) {
            recommandMoviesList.add(moviesList.get(i));
        }
        recommandBundle.putSerializable("moviesList", recommandMoviesList);
        RecommandFragment.setArguments(recommandBundle);
    }

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PIC && resultCode == RESULT_OK) {
            int imgid = data.getIntExtra("image", -1);
            if(imgid!=-1){
                headImage.setImageResource(imgid);
            }
        }
    }

}



