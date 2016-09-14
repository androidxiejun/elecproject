package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.electronicproject.FashionFragment.bean.DatasUtils;
import com.example.administrator.electronicproject.FashionFragment.bean.ExpertBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.ExpertAdapter;
import com.example.administrator.electronicproject.MainActivity;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunbin on 2016/9/9.
 * 衣橱达人界面
 */
public class ExpertActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.expert_bar_back_btn)
    Button backBtn;
    @BindView(R.id.expert_pull_list_view)
    PullToRefreshListView mPullView;
    @BindView(R.id.expert_pull_empty_iv)
    ImageView emptyIv;
    @BindView(R.id.expert_person_top_btn)
    Button topBackBtn;

    private List<ExpertBean.DataBean.ItemsBean> expertDatas = new ArrayList<>();

    private static int flag ;
    private ExpertAdapter expertAdapter;
    private ListView refreshableView;
    private boolean ifAddMore = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_layout);
        ButterKnife.bind(this);

        //每次进入，都是从头加载
        flag = 0;

        initView();
    }

    private void initView() {

        backBtn.setOnClickListener(this);
        topBackBtn.setOnClickListener(this);

        expertAdapter = new ExpertAdapter(this,expertDatas);
        mPullView.setAdapter(expertAdapter);

        mPullView.setMode(PullToRefreshBase.Mode.BOTH);

        //添加空视图
        refreshableView = mPullView.getRefreshableView();
        refreshableView.setEmptyView(emptyIv);
        AnimationDrawable animation = (AnimationDrawable) emptyIv.getDrawable();
        animation.start();

        initDatas();
        initListener();
    }

    /**
     * PullToRefreshListView的上拉加载更多
     */
    private void initListener() {

        /**
         * PullToRefreshListView的上拉加载更多
         */
        mPullView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //暂时不支持下拉刷新
//                initDatas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //参数flag已经在initDatas()中变更，这里直接调用即可，下拉刷新有动画但不支持刷新
                ifAddMore = true;
                initDatas();
            }
        });


        mPullView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i < 3){
                    topBackBtn.setVisibility(View.INVISIBLE);
                }else {
                    topBackBtn.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initDatas() {

        /**
         * 请求衣橱达人的数据
         */
        HttpUtils.create().experDatas(flag).enqueue(new Callback<ExpertBean>() {

            @Override
            public void onResponse(Call<ExpertBean> call, Response<ExpertBean> response) {
                //如果是下拉刷新，则清空原始数据
                if (!ifAddMore){
                    expertDatas.clear();
                }
                expertDatas.addAll(response.body().getData().getItems());
                expertAdapter.notifyDataSetChanged();

                flag = response.body().getData().getFlag();
            }

            @Override
            public void onFailure(Call<ExpertBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.expert_bar_back_btn:
                finish();
                break;
            case R.id.expert_person_top_btn:
                refreshableView.setSelection(0);
                break;
        }
    }
}
