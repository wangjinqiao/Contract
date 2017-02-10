package com.example.administrator.myapplication26;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication26.entity.HttpResponse;
import com.example.administrator.myapplication26.entity.HttpResponseLogin;
import com.example.administrator.myapplication26.net.CallBackUI;
import com.example.administrator.myapplication26.net.EasyShopClient;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 登录页面
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_username_login)
    EditText edtUsernameLogin;
    @BindView(R.id.edt_pwd_login)
    EditText edtPwdLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_register_login)
    TextView txtRegisterLogin;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        setBack();

        edtUsernameLogin.addTextChangedListener(textWatcher);
        edtPwdLogin.addTextChangedListener(textWatcher);
    }

    private void setBack() {
        //设置返回键
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick({R.id.btn_login, R.id.txt_register_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //登录
                visitHttpLogin();
                break;
            case R.id.txt_register_login:
                //去注册
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void visitHttpLogin() {
        EasyShopClient.getInstance().
                login(username, password).
                enqueue(new CallBackUI<HttpResponseLogin>() {
                    @Override
                    public void onFailureUI(Call call, IOException e) {
                        Toast.makeText(LoginActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseUI(Call call, HttpResponse<HttpResponseLogin> httpResponse) {
                        if (httpResponse.getCode()==1){
                            Toast.makeText(LoginActivity.this, "登录成功！" , Toast.LENGTH_SHORT).show();
                            toShop();
                        }else{
                            Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void toShop() {
        // TODO: 2017/2/10 0010  记录登录信息
        //跳转到主界面的市场页面
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    String username;
    String password;
    //Edt的监听事件
    public TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            username = edtUsernameLogin.getText().toString();
            password = edtPwdLogin.getText().toString();
            boolean canLogin = TextUtils.isEmpty(username) || TextUtils.isEmpty(password);
            btnLogin.setEnabled(!canLogin);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //返回键
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
