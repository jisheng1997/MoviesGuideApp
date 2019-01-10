package com.example.moviesguideapp;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LoginActivity extends BaseActivity {

    private ImageView imageView;
    private TextView textView;
    private LinearLayout linearLayout;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.login);
        imageView = findViewById(R.id.back);
        textView = findViewById(R.id.loginTo_register);
        linearLayout = findViewById(R.id.login_view);
        loginBtn = findViewById(R.id.login_btn);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        imageView.setOnClickListener(this);
        textView.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBack();
                break;
            case R.id.loginTo_register:
                openActivityAndCloseThis(RegisterActivity.class);
                overridePendingTransition(R.anim.slide_out_to_left,R.anim.slide_in_from_right);
                break;
            case R.id.login_view:
                hideSoftInput(linearLayout.getWindowToken());
                break;
            case R.id.login_btn:
                openActivityAndCloseThis(MainLoginActivity.class);
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

}
