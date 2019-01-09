package com.example.moviesguideapp;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LoginActivity extends BaseActivity {

    private ImageView imageView;
    private TextView textView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.login);
        imageView = findViewById(R.id.back);
        textView = findViewById(R.id.loginTo_register);
        linearLayout = findViewById(R.id.login_view);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        imageView.setOnClickListener(this);
        textView.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
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
            case R.id.login_view:
                hideSoftInput(linearLayout.getWindowToken());
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
