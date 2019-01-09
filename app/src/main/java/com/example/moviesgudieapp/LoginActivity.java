package com.example.moviesgudieapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    private ImageView imageView;
    private TextView textView;

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
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        imageView.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBack();
                break;
            case R.id.loginTo_register:
                openActivityAndCloseThis(RegisterActivity.class);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            default:
                break;
        }
    }

}
