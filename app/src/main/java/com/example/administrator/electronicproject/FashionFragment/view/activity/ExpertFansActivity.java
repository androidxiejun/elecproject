package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.FansBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.ExpertFansAdapter;
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
 * Created by sunbin on 2016/9/11.
 * 粉丝 和 关注的人 界面
 */
public class ExpertFansActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.expert_fans_tool_back_btn)
    Button backBtn;
    @BindView(R.id.expert_fans_tool_content_btn)
    TextView titleTv;
    @BindView(R.id.expert_fans_pull_list_view)
    PullToRefreshListView pullView;
    @BindView(R.id.expert_fans_empty_iv)
    ImageView emptyIv;

    private static int id ;
    private static int type ;
    private static int flag = 0;
    private Intent intent;
    private boolean ifAdd = false;

    private List<FansBean.DataBean.ItemsBean> items = new ArrayList<>();
    private ExpertFansAdapter expertFansAdapter;
    private ListView refreshableView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_fans_layout);
        ButterKnife.bind(this);

        intent = getIntent();
        id = intent.getIntExtra("id",0);
        type = intent.getIntExtra("type",1);

        initView();
    }

    private void initView() {
        if (type == 1){
            titleTv.setText("粉丝");
        }else {
            titleTv.setText("关注的人");
        }
        backBtn.setOnClickListener(this);

        expertFansAdapter = new ExpertFansAdapter(this,items);
        pullView.setAdapter(expertFansAdapter);
        refreshableView = pullView.getRefreshableView();
        refreshableView.setEmptyView(emptyIv);
        AnimationDrawable drawable = (AnimationDrawable) emptyIv.getDrawable();
        drawable.start();
        pullView.setMode(PullToRefreshBase.Mode.BOTH);

        initDadas();
        initListener();
    }

    /**
     * 设置下拉刷新和上拉加载更多监听
     */
    private void initListener() {
        pullView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                ifAdd = false;
                initDadas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉加载更多
                ifAdd = true;
                initDadas();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.expert_fans_tool_back_btn:
                finish();
                break;
        }
    }

    private void initDadas() {
        HttpUtils.create().expertFansDatas(flag,id,type).enqueue(new Callback<FansBean>() {


            @Override
            public void onResponse(Call<FansBean> call, Response<FansBean> response) {
                FansBean.DataBean dataBean = response.body().getData();
                if (dataBean != null){
                    flag = dataBean.getFlag();
                    if (! ifAdd){
                        items.clear();
                    }
                    items.addAll(dataBean.getItems());
                    expertFansAdapter.notifyDataSetChanged();
                    pullView.onRefreshComplete();
                }else {
                    //设置空视图
                    emptyIv.setImageResource(R.drawable.icon_loading_data_null);
                }
            }

            @Override
            public void onFailure(Call<FansBean> call, Throwable t) {

            }
        });
    }
}
