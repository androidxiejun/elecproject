package com.example.administrator.electronicproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.fragment.UserMobileFragmentOne;
import com.example.administrator.electronicproject.fragment.UserMobileFragmentTwo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/13.
 * 绑定手机号
 */
public class UserInfoMobileActivity extends AppCompatActivity implements View.OnClickListener,UserMobileFragmentOne.MobileCallBack {

    @BindView(R.id.user_mobile_back_btn)
    Button backBtn;
    @BindView(R.id.user_info_frame_layout)
    FrameLayout frameLayout;

    private FragmentManager manager;
    private Fragment mCurrentShowFragment;

    private Intent intent;
    private String mobile;
    private UserMobileFragmentOne userMobileFragmentOne;
    private UserMobileFragmentTwo userMobileFragmentTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_mobile_layout);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();
        intent = getIntent();
        mobile = intent.getStringExtra("mobile");

        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);

        userMobileFragmentOne = UserMobileFragmentOne.newInstance(mobile,this);
        userMobileFragmentTwo = UserMobileFragmentTwo.newInstance();

        chooseFragment(userMobileFragmentOne);//刚进入显示的界面
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_mobile_back_btn://返回
                finish();
                break;
        }
    }


    /**
     * 当点击RadioButton时会执行此方法，加载相应的fragment视图
     * @param fragment
     */
    private void chooseFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (mCurrentShowFragment != null && mCurrentShowFragment.isAdded()) {
            fragmentTransaction.hide(mCurrentShowFragment);
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.user_info_frame_layout,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }

    /**
     * 在上一个Fragment点击next时，加载下一个Fragment
     */
    @Override
    public void addNext() {
        chooseFragment(userMobileFragmentTwo);
    }
}
