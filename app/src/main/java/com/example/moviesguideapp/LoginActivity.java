/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:LoginActivity
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * version:1.12
 * author:
 * className:LoginActivity
 * date:2019/1/10 15:19
 */

public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";
    private ImageView back;
    private TextView loginTo_register;
    private LinearLayout linearLayout;
    private String userName;
    private String password;
    private Button loginBtn;
    private EditText account_EditText;
    private EditText password_EditText;
    private int id_user ;
    private int isSuccessful;
    private String data = null;
    private String curResponse = null;
    /**
     * override the onCreate function
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivityManager(this);
    }
    /**
     * override the initView function
     * initialize the Views
     */
    @Override
    protected void initView() {
        setContentView(R.layout.login);
        back = findViewById(R.id.back);
        loginTo_register = findViewById(R.id.loginTo_register);
        linearLayout = findViewById(R.id.login_view);
        loginBtn = findViewById(R.id.login_btn);
        account_EditText = findViewById(R.id.account_EditText);
        password_EditText = findViewById(R.id.password_EditText);
    }
    /**
     * override the initData function
     * initialize the Data
     */
    @Override
    protected void initData() {

    }
    /**
     * override the initListener function
     * initial the listener
     */
    @Override
    protected void initListener() {
        back.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        loginTo_register.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
    }
    /**
     * override the onClick function
     * initial the OnClickListener
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBack();
                overridePendingTransition(R.anim.push_in_from_left,R.anim.push_out_to_right);
                break;
            case R.id.loginTo_register:
                openActivityAndCloseThis(RegisterActivity.class);
                overridePendingTransition(R.anim.push_in_from_right,R.anim.push_out_to_left);
                break;
            case R.id.login_view:
                hideSoftInput(linearLayout.getWindowToken());
                break;
            case R.id.login_btn:
                login();
                break;
            default:
                break;
        }
    }

    /**
     * send login request which contains username and password
     * return the login result for different situations
     */
    public void login() {
        userName = account_EditText.getText().toString();
        password = password_EditText.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "username and password are not allowed to be empty", Toast.LENGTH_LONG).show();
            return;
        }
        data = "username=" + userName + "&password=" + password + "&login=";
        sendRequest(data,login_register_path);
        while(curResponse == null){
            curResponse = getResponse();
        }

        isSuccessful=Integer.parseInt(curResponse.substring(0,1));
        if (isSuccessful == 1) {
            id_user = Integer.parseInt(curResponse.substring(1,curResponse.length()-1));
            Bundle bundle = new Bundle();
            bundle.putInt("id_user", id_user);
            bundle.putString("username", userName);
            openActivityAndCloseThis(MainLoginActivity.class, bundle);
        } else if (isSuccessful == 2) {
            Toast.makeText(getApplicationContext(), "username and password do not match", Toast.LENGTH_SHORT).show();
        }
        curResponse = null;
    }

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
