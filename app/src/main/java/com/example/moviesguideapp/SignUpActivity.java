/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:SignUpActivity
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
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
 * className:SignUpActivity
 * date:2019/1/10 15:19
 */

public class SignUpActivity extends BaseActivity {

    public static final String TAG = "SignUpActivity";
    private ImageView back;
    private TextView loginTo_register;
    private LinearLayout linearLayout;
    private Button loginBtn;
    private EditText account_EditText;
    private EditText password_EditText;
    private String user;
    private String password;
    private String data = null;
    private String response = null;
    protected static final int SUCCESS = 1;
    protected static final int ERROR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.signup);
        back = findViewById(R.id.back);
        loginTo_register = findViewById(R.id.signUpTo_signIn);
        linearLayout = findViewById(R.id.signUp_view);
        loginBtn = findViewById(R.id.signUp_btn);
        account_EditText = findViewById(R.id.account_EditText);
        password_EditText = findViewById(R.id.password_EditText);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        back.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        loginTo_register.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBack();
                overridePendingTransition(R.anim.push_in_from_left,R.anim.push_out_to_right);
                break;
            case R.id.signUpTo_signIn:
                openActivityAndCloseThis(SignInActivity.class);
                overridePendingTransition(R.anim.push_in_from_right,R.anim.push_out_to_left);
                break;
            case R.id.signUp_view:
                hideSoftInput(linearLayout.getWindowToken());
                break;
            case R.id.signUp_btn:
                openActivityAndCloseThis(MainSignUpActivity.class);
                break;
            default:
                break;
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    Toast.makeText(SignUpActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case ERROR:
                    Toast.makeText(SignUpActivity.this, "please check your network", Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        }
    });

    public void login() {
        data = "username=" + user + "&password=" + password + "&signup=";
        user = account_EditText.getText().toString();
        password = password_EditText.getText().toString();

        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "username and password are not allowed to be empty", Toast.LENGTH_LONG).show();
            return;
        }
        sendRequest(data, handler);
    }

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}