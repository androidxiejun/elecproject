package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.FashionFragment.view.FashionGridViewAdapter;
import com.example.administrator.electronicproject.MainActivity;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunbin on 2016/9/8.
 * 点击标签，进入的界面。从标签中获取id。
 */
public class FashionTableDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.fashion_details_bar_back_btn)
    Button backBtn;
    @BindView(R.id.fashion_table_details_title_tv)
    TextView titleTv;
    @BindView(R.id.fashion_table_details_commend_tv)
    TextView commendTv;
    @BindView(R.id.fashion_tabledetails_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.fashion_table_details_pull_grid_view)
    PullToRefreshGridView mGridView;
    @BindView(R.id.fashion_table_details_pull_empty_iv)
    ImageView emptyIv;

    private Intent intent;
    private Context context;
    private int tag_id;
    private int recommend = 1;
    private String flag = "";
    private List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> tableDatas = new ArrayList<>();
    private FashionGridViewAdapter fashionGridViewAdapter;
    private GridView refreshableView;
    //用于判断进入FashionTableDetailsActivity界面的是哪个界面，用于 FashionTableDetailsActivity的返回
    private String come;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fashion_table_details);
        ButterKnife.bind(this);

        context = this;
        intent = getIntent();
        tag_id = intent.getIntExtra("id",607);
        come = intent.getStringExtra("come");

        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        commendTv.setOnClickListener(this);

        tabLayout.addTab(tabLayout.newTab().setText("精选"));
        tabLayout.addTab(tabLayout.newTab().setText("最新"));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //获取PullToRefreshGridView的gridview，用来添加空视图
        refreshableView = mGridView.getRefreshableView();
        refreshableView.setEmptyView(emptyIv);
        //获得帧动画对象
        AnimationDrawable animationDrawable = (AnimationDrawable) emptyIv.getDrawable();
        animationDrawable.start();
        //为PullToRefreshGridView绑定适配器
        fashionGridViewAdapter = new FashionGridViewAdapter(this,tableDatas,null);
        mGridView.setAdapter(fashionGridViewAdapter);

        initDatas();
        initListener();
    }

    private void initListener() {

        /**
         * TabLayout的Tab选择监听，用来选择加载最新还是精选
         */
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        recommend = 1;
                        initDatas();
                        break;
                    case 1:
                        recommend = 0;
                        initDatas();
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

        /**
         * gridview的item点击跳转
         */
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context,FashionTopDetailsActivity.class);
                intent.putExtra("id",tableDatas.get(i).getComponent().getId());
                intent.putExtra("thread_id",tableDatas.get(i).getComponent().getId()+"");
                intent.putExtra("come","table");
                startActivity(intent);
//                finish();//那边需要返回，不能finish（）；
            }
        });
    }

    /**
     * 请求标签 详情和最新 界面的数据
     */
    private void initDatas() {
        HttpUtils.create().fashionTableDeatilsDatas(recommend,flag,tag_id).enqueue(new Callback<FashionBottonBean>() {

            @Override
            public void onResponse(Call<FashionBottonBean> call, Response<FashionBottonBean> response) {
                FashionBottonBean.ResponseBean.DataBean dataBean = response.body().getResponse().getData();
                titleTv.setText("#"+dataBean.getTagHead().getTitle());
                if (tableDatas != null){
                    tableDatas.clear();
                }
                tableDatas.addAll(dataBean.getItems());
                fashionGridViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FashionBottonBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //点击回退按钮进行回退
            case R.id.fashion_details_bar_back_btn:
                Intent intent = null;
                if (come.equals("table")){
                    intent = new Intent(this,FashionMiddleTableActivity.class);
                    startActivity(intent);
                    finish();
                }else if(come.equals("middle")){
//                    intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                }else if (come.equals("topdetails")){
//                    intent = new Intent(this,FashionTopDetailsActivity.class);
                    finish();
                }
//                startActivity(intent);
//                finish();
                break;
            //点击关注按钮，关注商品
            case R.id.fashion_table_details_commend_tv:
                //TODO
                break;
        }
    }
}
