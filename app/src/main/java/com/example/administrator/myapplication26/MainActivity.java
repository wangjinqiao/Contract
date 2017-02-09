package com.example.administrator.myapplication26;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication26.fragment.MyFragment;
import com.example.administrator.myapplication26.fragment.ShopFragment;
import com.example.administrator.myapplication26.fragment.UnlandFragment;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.title_toolbar_main)
    TextView titleToolbarMain;


    @BindView(R.id.vpg_main)
    ViewPager vpgMain;

    @BindViews({R.id.txt_shop, R.id.txt_mes, R.id.txt_tongxunlu, R.id.txt_my})
    TextView[] txtButtoms;


    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    //初始化视图
    private void initView() {
        txtButtoms[0].setSelected(true);
        vpgMain.setAdapter(mPagerAdapter);
        //更换内容
        vpgMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 4; i++) {
                    txtButtoms[i].setSelected(false);
                }
                //设置标题
                titleToolbarMain.setText(txtButtoms[position].getText());
                //设置选中图标
                txtButtoms[position].setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 点击图标更换内容，标题
     *
     * @param view
     */
    @OnClick({R.id.txt_shop, R.id.txt_mes, R.id.txt_tongxunlu, R.id.txt_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_shop:
                //市场
                vpgMain.setCurrentItem(0, false);
                break;
            case R.id.txt_mes:
                // 消息
                vpgMain.setCurrentItem(1, false);
                break;
            case R.id.txt_tongxunlu:
                //通讯录
                vpgMain.setCurrentItem(2, false);
                break;
            case R.id.txt_my:
                //我的
                vpgMain.setCurrentItem(3, false);
                break;
            default:
                break;
        }
    }

    //viewPager 的适配器
    FragmentStatePagerAdapter mPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

        @Override
        public int getCount() {
            //4个页面
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ShopFragment();
                //未登录
                case 1:
                case 2:
                    return new UnlandFragment();
                case 3:
                    return new MyFragment();
                default:
                    return null;
            }
        }
    };

    //再按一次退出
    public boolean isExit = false;

    @Override
    public void onBackPressed() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            new TextView(this).postDelayed(new Runnable() {
                @Override
                public void run() {
                    //3s内没有再按一次
                    isExit = false;
                }
            }, 3000);
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        //解除绑定
        unbinder.unbind();
        super.onDestroy();
    }
}
