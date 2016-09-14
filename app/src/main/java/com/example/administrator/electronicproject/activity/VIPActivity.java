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
import android.widget.ImageView;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.fragment.VIPPersonFeagment;
import com.example.administrator.electronicproject.fragment.VIPRuleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/13.
 * 会员专享界面
 */
public class VIPActivity extends AppCompatActivity implements View.OnClickListener,VIPPersonFeagment.VIPCallBack{

    @BindView(R.id.vip_back_btn)
    Button backBtn;
    @BindView(R.id.vip_message)
    ImageView message;
    @BindView(R.id.vip_shopping_cart)
    ImageView shoppingCart;
    private Intent intent;
    private int uid;
    private FragmentManager manager;
    private Fragment mCurrentShowFragment;
    private VIPPersonFeagment vipPersonFeagment;
    private VIPRuleFragment vipRuleFragment;
    private static int whitchFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vip_layout);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();
        intent = getIntent();
        uid = intent.getIntExtra("uid",13894726);
        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        message.setOnClickListener(this);
        shoppingCart.setOnClickListener(this);

        vipPersonFeagment = VIPPersonFeagment.newInstance(uid,this);
        vipRuleFragment = VIPRuleFragment.newInstance();

        chooseFragment(vipPersonFeagment);
        whitchFragment = 1;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vip_back_btn:
                if (whitchFragment == 1){
                    finish();
                }
                if (whitchFragment == 2){
                    chooseFragment(vipPersonFeagment);
                    whitchFragment = 1;
                }
                break;
            case R.id.vip_message://跳转到消息界面
                break;
            case R.id.vip_shopping_cart://跳转到购物车界面
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
            fragmentTransaction.add(R.id.vip_frame_layout,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }

    @Override
    public void ruleFragment() {
        chooseFragment(vipRuleFragment);
        whitchFragment = 2;
    }
}
