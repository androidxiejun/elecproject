package com.example.administrator.electronicproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.fragment.MyOrderAllFragment;
import com.example.administrator.electronicproject.fragment.MyOrderEvaluateFragment;
import com.example.administrator.electronicproject.fragment.MyOrderPayFragment;
import com.example.administrator.electronicproject.fragment.MyOrderReturnFragment;
import com.example.administrator.electronicproject.fragment.MyOrderSendFragment;
import com.example.administrator.electronicproject.fragment.MyOrderTackFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/12.
 * 我的商城订单
 */
public class MyOrderActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.order_back_btn)
    Button backBtn;
    @BindView(R.id.order_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.order_view_pager)
    ViewPager viewPager;

    private List<Fragment> viewPagerList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private OrderAdapter orderAdapter;

    //用来判断viewpager，在最左端向右滑动时，销毁当前activity
    private static float startX = 0;
    private static float startY = 0;
    private static float stopX = 0;
    private static float stopY = 0;
    private Intent intent;
    private int num;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order_layout);
        ButterKnife.bind(this);

        intent = getIntent();
        num = intent.getIntExtra("num",0);
        fragmentManager = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);

        tabLayout.addTab(tabLayout.newTab().setText("全部"));
        tabLayout.addTab(tabLayout.newTab().setText("待付款"));
        tabLayout.addTab(tabLayout.newTab().setText("待发货"));
        tabLayout.addTab(tabLayout.newTab().setText("待收货"));
        tabLayout.addTab(tabLayout.newTab().setText("待评价"));
        tabLayout.addTab(tabLayout.newTab().setText("退款/退货"));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        orderAdapter = new OrderAdapter(fragmentManager);
        viewPager.setAdapter(orderAdapter);

        viewPager.setCurrentItem(num);

        initDatas();
        initListener();
    }

    private void initDatas() {
        viewPagerList.add(MyOrderAllFragment.newInstance());
        viewPagerList.add(MyOrderPayFragment.newInstance());
        viewPagerList.add(MyOrderSendFragment.newInstance());
        viewPagerList.add(MyOrderTackFragment.newInstance());
        viewPagerList.add(MyOrderEvaluateFragment.newInstance());
        viewPagerList.add(MyOrderReturnFragment.newInstance());

        orderAdapter.notifyDataSetChanged();
    }

    private void initListener() {
        /**
         * TabLayput选择监听，控制viewpager的显示
         */
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /**
         * viewpager的监听，控制TabLayout的显示
         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
                float x = stopX - startX ;
                float y = stopY - startY;
                if (position == 0){
                    if (x > 0 && y >= 0 && x > y){
                        finish();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            startX = event.getX();
            startY = event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP){
            stopX = event.getX();
            startY = event.getY();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_back_btn:
                finish();
                break;
        }
    }


    class OrderAdapter extends FragmentStatePagerAdapter {

        public OrderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return viewPagerList.get(position);
        }

        @Override
        public int getCount() {
            return viewPagerList == null ? 0 : viewPagerList.size();
        }
    }
}
