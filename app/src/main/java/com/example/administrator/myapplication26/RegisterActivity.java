package com.example.administrator.myapplication26;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication26.entity.HttpResponse;
import com.example.administrator.myapplication26.entity.HttpResponseLogin;
import com.example.administrator.myapplication26.entity.HttpResponseRegister;
import com.example.administrator.myapplication26.net.CallBackUI;
import com.example.administrator.myapplication26.net.EasyShopClient;
import com.example.administrator.myapplication26.utils.RegexUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    private String passwordAgain;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        unbinder = ButterKnife.bind(this);
        setBack();

        etUsername.addTextChangedListener(textWatcher);
        etPwd.addTextChangedListener(textWatcher);
        edtPwdAgain.addTextChangedListener(textWatcher);
    }

    //Edt的监听事件
    public TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            username = etUsername.getText().toString();
            password = etPwd.getText().toString();
            passwordAgain = edtPwdAgain.getText().toString();
            boolean canLogin = TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordAgain);
            btnRegister.setEnabled(!canLogin);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    //设置返回键
    private void setBack() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //注册按钮
    @OnClick(R.id.btn_register)
    public void onClick() {
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
            Toast.makeText(this, R.string.username_rules, Toast.LENGTH_SHORT).show();
            return;
        } else if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
            Toast.makeText(this, R.string.password_rules, Toast.LENGTH_SHORT).show();
            return;
        } else if (!TextUtils.equals(password, passwordAgain)) {
            Toast.makeText(this, R.string.username_equal_pwd, Toast.LENGTH_SHORT).show();
            return;
        }

        visitHttpRegister();

    }

    private void visitHttpRegister() {
        //注册
        EasyShopClient.getInstance().register(username, password)
                .enqueue(new CallBackUI<HttpResponseRegister>() {
                    @Override
                    public void onFailureUI(Call call, IOException e) {
                        Toast.makeText(RegisterActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseUI(Call call, HttpResponse<HttpResponseRegister> httpResponse) {
                        if(httpResponse.getCode()==1) {
                            Toast.makeText(RegisterActivity.this, "注册成功！" , Toast.LENGTH_SHORT).show();
                            loginning();
                        }else {
                            Toast.makeText(RegisterActivity.this, httpResponse.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void loginning() {
        //直接进行登录并记录登录信息，然后跳转到主界面的市场页面
        visitHttpLogin();
    }
    private void visitHttpLogin() {
        EasyShopClient.getInstance().
                login(username, password).
                enqueue(new CallBackUI<HttpResponseLogin>() {
                    @Override
                    public void onFailureUI(Call call, IOException e) {
                        Toast.makeText(RegisterActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseUI(Call call, HttpResponse<HttpResponseLogin> httpResponse) {
                        Toast.makeText(RegisterActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        toShop();
                    }
                });
    }

    private void toShop() {
        // TODO: 2017/2/10 0010 记录登录信息 
        //跳转到主界面的市场页面
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        finish();
    }

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
        unbinder.unbind();
        super.onDestroy();
    }
}
