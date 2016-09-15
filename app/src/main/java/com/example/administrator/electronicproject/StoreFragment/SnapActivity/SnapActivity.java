package com.example.administrator.electronicproject.StoreFragment.SnapActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapFragment.SnapingFragment;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapFragment.StartingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SnapActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.snap_back_btn)
    Button backBtn;
    @BindView(R.id.snap_snaping_btn)
    Button snapingBtn;
    @BindView(R.id.snap_start_btn)
    Button startBtn;
    @BindView(R.id.snap_tab_layout)
    TabLayout mTabLayout;
    private Context context;
    private FragmentManager manager;
    private SnapingFragment snapingFragment;
    private StartingFragment startingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap);
        context = this;
        ButterKnife.bind(this);
        snapingBtn.setBackgroundColor(Color.RED);
        manager=getSupportFragmentManager();
        snapingFragment=SnapingFragment.newInstance();
        startingFragment=StartingFragment.newInstance();
        initFragment(snapingFragment);
        initListenner();
    }

    private void initFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.snap_fragment,fragment);
        fragmentTransaction.commit();
    }


    private void initListenner() {
        backBtn.setOnClickListener(this);
        snapingBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        //对tablayout进行点击监听，然后刷新界面
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选择对应的tab item,进行界面刷新
                //TODO
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
        switch (view.getId()) {
            //点击回退键，退出当前界面
            case R.id.snap_back_btn:
                finish();
                break;
            //点击“正在热抢”键，跳转至该界面
            case R.id.snap_snaping_btn:
                initFragment(snapingFragment);
                snapingBtn.setBackgroundColor(Color.RED);
                startBtn.setBackgroundColor(Color.WHITE);
                break;
            //点击“即将开始”键，跳转至该界面
            case R.id.snap_start_btn:
                initFragment(startingFragment);
                snapingBtn.setBackgroundColor(Color.WHITE);
                startBtn.setBackgroundColor(Color.RED);
                break;
        }
    }
}
