package com.example.administrator.electronicproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.fragment.UserBrandFragment;
import com.example.administrator.electronicproject.fragment.UserPostFragment;
import com.example.administrator.electronicproject.fragment.UserPostFragmentStart;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/14.
 * 关注的品牌界面
 */
public class UserBrandActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.user_brand_back_btn)
    Button backBtn;
    private FragmentManager manager;
    private Fragment mCurrentShowFragment;
    private UserPostFragmentStart startFragment;
    private UserBrandFragment userBrandFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_brand_layout);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();

        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);

        startFragment = UserPostFragmentStart.newInstance();
        userBrandFragment = UserBrandFragment.newInstance();

        chooseFragment(startFragment);
        /**
         * 模拟网络更新数据
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                chooseFragment(userBrandFragment);//数据更新完，刷新适配器，使用Hnadler
            }
        }).start();
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
            fragmentTransaction.add(R.id.user_brand_frame,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_brand_back_btn:
                finish();
                break;
        }
    }
}
