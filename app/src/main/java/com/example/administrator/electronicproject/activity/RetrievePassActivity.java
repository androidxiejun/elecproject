package com.example.administrator.electronicproject.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.fragment.RetrieveFragmentOne;
import com.example.administrator.electronicproject.fragment.RetrieveFragmentTwo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/12.
 * 找回密码界面
 */
public class RetrievePassActivity extends AppCompatActivity implements View.OnClickListener,RetrieveFragmentOne.RetrieveCallBackOne {

    @BindView(R.id.retrieve_pass_back_btn)
    Button backBtn;
    private FragmentManager manager;
    private Fragment mCurrentShowFragment;
    private RetrieveFragmentOne retrieveFragmentOne;
    private RetrieveFragmentTwo retrieveFragmentTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_pass_layout);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();
        initListener();
    }

    private void initListener() {
        backBtn.setOnClickListener(this);

        retrieveFragmentOne = RetrieveFragmentOne.newInstance(this);
        retrieveFragmentTwo = RetrieveFragmentTwo.newInstances();

        chooseFragment(retrieveFragmentOne);//第一次进入，选择fragmentone
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.retrieve_pass_back_btn://返回按钮
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
            fragmentTransaction.add(R.id.retrieve_pass_fragment_layout,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }

    /**
     * 当点击FragmentOne中的下一步时，使用接口回调，改变fragment
     */
    @Override
    public void callBack() {
        chooseFragment(retrieveFragmentTwo);
    }
}
