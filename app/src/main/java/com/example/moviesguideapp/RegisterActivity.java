/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:RegisterActivity
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
 * className:RegisterActivity
 * date:2019/1/10 15:19
 */

public class RegisterActivity extends BaseActivity {
    /**
     * The constant TAG.
     */
    public static final String TAG = "RegisterActivity";
    private ImageView back;
    private TextView registerTo_login;
    private LinearLayout linearLayout;
    private Button registerBtn;
    private EditText account_EditText;
    private EditText password_EditText;
    private EditText confirm_password_EditText;
    private String password;
    private String password_confirm;
    private String data;
    private String userName;
    private String curResponse;
    private String path = "http://192.168.0.139:8081/MoviesGuideApp/login&register.php";
    private int isSuccessful;
    private int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivityManager(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.register);
        back = findViewById(R.id.back);
        registerTo_login = findViewById(R.id.registerTo_login);
        linearLayout = findViewById(R.id.register_view);
        registerBtn = findViewById(R.id.register_btn);
        account_EditText = findViewById(R.id.account_EditText);
        password_EditText = findViewById(R.id.password_EditText);
        confirm_password_EditText = findViewById(R.id.confirm_password_EditText);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        back.setOnClickListener(this);
        registerTo_login.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBack();
                overridePendingTransition(R.anim.push_in_from_left, R.anim.push_out_to_right);
                break;
            case R.id.registerTo_login:
                openActivityAndCloseThis(LoginActivity.class);
                overridePendingTransition(R.anim.push_in_from_left, R.anim.push_out_to_right);
                break;
            case R.id.register_view:
                hideSoftInput(linearLayout.getWindowToken());
                break;
            case R.id.register_btn:
                register();
                break;
            default:
                break;
        }
    }

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void register() {
        userName = account_EditText.getText().toString();
        password = password_EditText.getText().toString();
        password_confirm = confirm_password_EditText.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password_confirm)) {
            Toast.makeText(this, "username and password are not allowed to be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(password_confirm)){
            Toast.makeText(this, "Two passwords must be same ", Toast.LENGTH_LONG).show();
            return;
        }
        data = "username=" + userName + "&password=" + password + "&register=";
        sendRequest(data, path);
        while (curResponse == null) {
            curResponse = getResponse();
        }
        isSuccessful = Integer.parseInt(curResponse.substring(0, 1));
        if (isSuccessful == 1) {
            Toast.makeText(getApplicationContext(), "username already exists", Toast.LENGTH_SHORT).show();
        } else if (isSuccessful == 2) {
            id_user = Integer.parseInt(curResponse.substring(1, curResponse.length() - 1));
            Bundle bundle = new Bundle();
            bundle.putInt("id_user", id_user);
            bundle.putString("username", userName);
            openActivityAndCloseThis(MainLoginActivity.class, bundle);
            Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_SHORT).show();
        } else if (isSuccessful == 3) {
            Toast.makeText(getApplicationContext(), "register error", Toast.LENGTH_SHORT).show();
        }
        curResponse = null;
    }
}