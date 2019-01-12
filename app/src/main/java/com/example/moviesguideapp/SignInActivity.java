/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:SignInActivity
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
 * className:SignInActivity
 * date:2019/1/10 15:19
 */

public class SignInActivity extends BaseActivity {
    public static final String TAG="SignInActivity";
    private ImageView back;
    private TextView registerTo_login;
    private LinearLayout linearLayout;
    private Button registerBtn;
    private EditText account_EditText;
    private EditText password_EditText;
    private EditText confirm_password_EditText;
    private String user;
    private String password;
    private String password_confirm;
    private String data;
    private String response;

    protected static final int SUCCESS = 1;
    protected static final int ERROR = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.signin);
        back = findViewById(R.id.back);
        registerTo_login = findViewById(R.id.signInTo_signUp);
        linearLayout = findViewById(R.id.signIn_view);
        registerBtn = findViewById(R.id.signIn_btn);
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
                overridePendingTransition(R.anim.push_in_from_left,R.anim.push_out_to_right);
                break;
            case R.id.signInTo_signUp:
                openActivityAndCloseThis(SignUpActivity.class);
                overridePendingTransition(R.anim.push_in_from_left,R.anim.push_out_to_right);
                break;
            case R.id.signIn_view:
                hideSoftInput(linearLayout.getWindowToken());
                break;
            case R.id.signIn_btn:
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
                    Toast.makeText(SignInActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case ERROR:
                    Toast.makeText(SignInActivity.this, "please check your network", Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        }
    });


    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void register(){
        data = "username="+user+"&password="+password+"&signin=";
        user = account_EditText.getText().toString();
        password = password_EditText.getText().toString();
        password_confirm = confirm_password_EditText.getText().toString();
        if (TextUtils.isEmpty(user)||TextUtils.isEmpty(password)||TextUtils.isEmpty(password_confirm)){
            Toast.makeText(this, "username and password are not allowed to be empty", Toast.LENGTH_LONG).show();
            return;
        }
        sendRequest(data,handler);
    }

    @Override
    protected void parseJSONWithJSON(String jsonData) {

    }

}