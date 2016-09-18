package com.example.administrator.electronicproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.electronicproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunbin on 2016/9/17.
 * 消息界面可共用的部分
 */
public class MessageItemFragment extends Fragment{

    private Context context;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private FragmentManager manager;
    private MyAdapter myAdapter;
    private List<Fragment> pagerList = new ArrayList<>();

    public static MessageItemFragment newInstance(){
        return new MessageItemFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        manager = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.message_item_layout, container, false);
        tablayout = (TabLayout) inflate.findViewById(R.id.message_item_tab_layout);
        viewPager = (ViewPager) inflate.findViewById(R.id.message_item_view_pager);

        initView();
        return inflate;
    }

    private void initView() {
        tablayout.addTab(tablayout.newTab().setText("客服"));
        tablayout.addTab(tablayout.newTab().setText("回复"));
        tablayout.addTab(tablayout.newTab().setText("评论"));
        tablayout.addTab(tablayout.newTab().setText("通知"));
        tablayout.setTabMode(TabLayout.MODE_FIXED);

        initDatas();
    }

    private void initDatas() {

        pagerList.add(MessageServiceFragment.newInstance());
        pagerList.add(MessageReplyFragment.newInstance());
        pagerList.add(MessageRecommendFragment.newInstance());
        pagerList.add(MessageNotifactionFragment.newInstance());

        myAdapter = new MyAdapter(manager);
        viewPager.setAdapter(myAdapter);

        initListener();
    }

    private void initListener() {
        /**
         * TabLayout的选择监听
         */
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
         * ViewPager的监听
         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return pagerList.get(position);
        }

        @Override
        public int getCount() {
            return pagerList == null? 0 : pagerList.size();
        }
    }
}
