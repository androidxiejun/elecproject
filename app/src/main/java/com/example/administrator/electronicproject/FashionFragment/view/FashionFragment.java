package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionMiddleBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/5.
 *
 */
public class FashionFragment extends Fragment implements View.OnClickListener{

    private Context context;
    @BindView(R.id.fashion_tool_bar_search_btn)
    Button searchBtn;
    @BindView(R.id.fashion_tool_bar_tab_layout)
    TabLayout toolBarTab;
    @BindView(R.id.fashion_tool_bar_camera_btn)
    Button cemeraBtn;
    @BindView(R.id.fashion_fragment_layout)
    FrameLayout fashionFrameLayout;
    private FragmentManager fragmentManager;
    private Fragment mCurrentShowFragment;
    private FashionRecommendFragment mRecommendFragment;
    private FashionNewestFragment mNewestFragment;

    public static FashionFragment newInstance(){
        return new FashionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        fragmentManager = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //时尚圈总布局
        View inflate = inflater.inflate(R.layout.fashion_fragment_this, container, false);
        ButterKnife.bind(this,inflate);

        initView();
        return inflate;
    }

    /**
     * 初始化各个控件
     */
    private void initView() {
        searchBtn.setOnClickListener(this); //搜索按钮的点击监听
        cemeraBtn.setOnClickListener(this); //照相机按钮的点击监听

        //TabLayout添加tab和设置监听
        toolBarTab.addTab(toolBarTab.newTab().setText("关注"));
        toolBarTab.addTab(toolBarTab.newTab().setText("推荐"));
        toolBarTab.addTab(toolBarTab.newTab().setText("最新"));
        toolBarTab.setTabMode(TabLayout.MODE_FIXED);

        //初始化fragment
        mRecommendFragment = FashionRecommendFragment.newInstance();
        mNewestFragment = FashionNewestFragment.newInstance();
        //设置默认选中推荐
        toolBarTab.getTabAt(1).select();
        chooseFragment(mRecommendFragment);

        initListener();
    }

    /**
     * 各控件的监听事件
     */
    private void initListener() {
        /**
         * TabLayout的tab点击监听
         */
        toolBarTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        break;
                    case 1:
                        chooseFragment(mRecommendFragment);
                        break;
                    case 2:
                        chooseFragment(mNewestFragment);
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
        //TODO
    }


    /**
     * 当点击TabLayout对应的Tab时会执行此方法，加载相应的fragment视图
     * @param fragment
     */
    private void chooseFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (mCurrentShowFragment != null && mCurrentShowFragment.isAdded()) {
            fragmentTransaction.hide(mCurrentShowFragment);
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fashion_fragment_layout,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }

}
