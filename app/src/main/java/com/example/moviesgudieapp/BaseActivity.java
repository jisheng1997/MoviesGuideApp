package com.example.moviesgudieapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Stack;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private static Stack<Activity> listActivity = new Stack<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
    }


    protected void initView(){

    }

    protected void initData(){

    }

    protected void initListener(){

    }

    @Override
    public void onClick(View view){

    }
}
