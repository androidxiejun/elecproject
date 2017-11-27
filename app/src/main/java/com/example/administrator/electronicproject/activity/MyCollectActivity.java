package com.example.administrator.electronicproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.fragment.MyCollectFragmentOne;
import com.example.administrator.electronicproject.fragment.MyCollectionFragmentTwo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/12.
 * 我的收藏界面
 */
public class MyCollectActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.my_collect_back_btn)
    Button backBtn;
    @BindView(R.id.my_collect_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.my_collect_fragment_layout)
    FrameLayout frameLayout;

    private FragmentManager manager;
    private Fragment mCurrentShowFragment;
    private MyCollectFragmentOne myCollectFragmentOne;
    private MyCollectionFragmentTwo myCollectionFragmentTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_collect_layout);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();
        initListener();
    }

    private void initListener() {
        backBtn.setOnClickListener(this);

        myCollectFragmentOne = MyCollectFragmentOne.newInstances();
        myCollectionFragmentTwo = MyCollectionFragmentTwo.newInstance();

        tabLayout.addTab(tabLayout.newTab().setText("单品"));
        tabLayout.addTab(tabLayout.newTab().setText("帖子"));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        chooseFragment(myCollectFragmentOne);
                        break;
                    case 1:
                        chooseFragment(myCollectionFragmentTwo);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_collect_back_btn:
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
            fragmentTransaction.add(R.id.my_collect_fragment_layout,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }
}
