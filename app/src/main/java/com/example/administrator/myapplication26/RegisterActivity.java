package com.example.administrator.myapplication26;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication26.net.EasyShopClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.edt_pwd_again)
    EditText edtPwdAgain;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private String username;
    private String password;
    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        //注册
        EasyShopClient.getInstance().register(username,password);
    }


    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
