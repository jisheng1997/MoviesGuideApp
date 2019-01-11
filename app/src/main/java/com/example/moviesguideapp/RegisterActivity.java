/**
 * project name:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:RegisterActivity
 * data:2019/1/10 15:19
 */

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


public class RegisterActivity extends BaseActivity {
    private ImageView imageView;
    private TextView textView;
    private LinearLayout linearLayout;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.register);
        imageView = findViewById(R.id.back);
        textView = findViewById(R.id.registerTo_login);
        linearLayout = findViewById(R.id.register_view);
        registerBtn = findViewById(R.id.register_btn);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        imageView.setOnClickListener(this);
        textView.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBack();
                overridePendingTransition(R.anim.push_in_from_left,R.anim.push_out_to_right);
                break;
            case R.id.registerTo_login:
                openActivityAndCloseThis(LoginActivity.class);
                overridePendingTransition(R.anim.push_in_from_left,R.anim.push_out_to_right);
                break;
            case R.id.register_view:
                hideSoftInput(linearLayout.getWindowToken());
                break;
            case R.id.register_btn:
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
