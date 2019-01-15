/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:BaseActivity
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * version:1.12
 * className:BaseActivity
 * date:2019/1/10 15:19
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public static ActivityManager activityManager = new ActivityManager();
    public static String movie_operation_path = "http://192.168.0.139:8081/MoviesGuideApp/movie_operation.php";
    public static String login_register_path = "http://192.168.0.139:8081/MoviesGuideApp/login&register.php";
    private String response = null;
    public static final String TAG = "dou ban jiang";

    /**
     * override the onCreate function
     *
     * @param savedInstanceState savedInstanceState
     */
    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
        hideBar();
        ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput(view.getWindowToken());
            }
        });
    }


    /**
     * initialize the views
     */
    protected void initView() {

    }

    /**
     * initialize the data
     */
    protected void initData() {

    }

    /**
     * initialize the listeners
     */
    protected void initListener() {

    }

    /**
     * override the onClick function
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
    }

    /**
     * Open activity with bundle as data
     *
     * @param targetActivityClass the target activity class
     * @param bundle              the bundle
     */
    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * Open activity.
     *
     * @param targetActivityClass the target activity class
     */
    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    /**
     * Open activity and close last activity
     *
     * @param targetActivityClass the target activity class
     */
    public void openActivityAndCloseThis(Class<?> targetActivityClass) {
        openActivity(targetActivityClass);
        this.finish();
    }

    /**
     * Open activity with bundle as data and close last activity
     *
     * @param targetActivityClass the target activity class
     * @param bundle              the bundle
     */
    public void openActivityAndCloseThis(Class<?> targetActivityClass, Bundle bundle) {
        openActivity(targetActivityClass, bundle);
        this.finish();
    }

    /**
     * get the data from the server
     *
     * @param request
     */
    public void sendRequest(final String request, final String path) {
        new Thread() {
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0(compatible;MSIE 9.0;Windows NT 6.1;Trident/5.0)");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length", request.length() + "");
                    conn.setDoOutput(true);
                    byte[] bytes = request.getBytes();
                    conn.getOutputStream().write(bytes);
                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        response = parseInfo(is);
                        Log.d(TAG, "dou ban jiang test : " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        response = null;
    }

    /**
     * get the response
     *
     * @return response
     */
    public String getResponse() {
        return response;
    }

    /**
     * get the response
     *
     * @return response
     */
    protected String parseInfo(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }


    /**
     * parsing String(response) with JSONObject
     */
    protected void parseJSONWithJSON(String jsonData) {
    }

    /**
     * Show short toast.
     *
     * @param text the text
     */
    public void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show long toast.
     *
     * @param text the text
     */
    public void showLongToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    /**
     * back to the last activity
     */
    public void onBack() {
        finish();
    }

    /**
     * refresh the current activity
     */
    public void onFresh() {
        onCreate(null);
    }

    /**
     * Hide status bar and navigation bar
     */
    public void hideBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
