package com.example.administrator.myapplication26.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication26.LoginActivity;
import com.example.administrator.myapplication26.MainActivity;
import com.example.administrator.myapplication26.R;
import com.example.administrator.myapplication26.StartActivity;
import com.pkmmte.view.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的页面
 */

public class MyFragment extends Fragment {
    public View view;
    @BindView(R.id.circle_img_head_icon)
    CircularImageView circleImgHeadIcon;
    @BindView(R.id.txt_register_login)
    TextView txtRegisterLogin;
    @BindView(R.id.txt_person_info)
    TextView txtPersonInfo;
    @BindView(R.id.txt_my_goods)
    TextView txtMyGoods;
    @BindView(R.id.txt_my_upload_goods)
    TextView txtMyUploadGoods;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_my, container, false);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.circle_img_head_icon, R.id.txt_register_login, R.id.txt_person_info, R.id.txt_my_goods, R.id.txt_my_upload_goods})
    public void onClick(View view) {
        //跳转到登录页面
        startActivity(new Intent(getActivity(), LoginActivity.class));

        switch (view.getId()) {
            case R.id.circle_img_head_icon:
                break;
            case R.id.txt_register_login:
                break;
            case R.id.txt_person_info:
                break;
            case R.id.txt_my_goods:
                break;
            case R.id.txt_my_upload_goods:
                break;
        }
    }
}
