package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionMiddleBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.FashionFragment.view.activity.FashionMiddleTableActivity;
import com.example.administrator.electronicproject.FashionFragment.view.activity.FashionTableDetailsActivity;
import com.example.administrator.electronicproject.FashionFragment.view.activity.FashionTopDetailsActivity;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunbin on 2016/9/6.
 * 时尚圈  推荐部分的fragment，分为两个部分
 * 1：上部分的banner，作为PullToRefreshListView的item，PullToRefreshListView只有一个item,为了保证有下拉刷新
 * 2: banner以下，两个自定义的GridView
 * 1：主体部分CustomGridVieew，作为PullToRefreshListView的item，PullToRefreshListView只有一个item,为了保证有下拉刷新
 * 2：头部，一个CustomGrid和一个banner
 */
public class FashionRecommendFragment extends Fragment implements View.OnClickListener {

    private Context context;

    //主体部分，PullToRefreshListView及其适配器和数据源
    private PullToRefreshListView fashionListView;
    private FashionListViewAdapter fashionListViewAdapter;
    //PullToRefreshListView的listview，用来加载下部分
    private ListView refreshableView;

    //头部Banner暂时使用的数据
    //暂时使用的数据源
    private int[] id = {469139,469172,469162,469105,468584,469114};
    private String[] images= new String[] {
            "http://s3.mingxingyichu.cn/group6/M00/AE/2F/wKgBjVfNQEyASrljAALE86r9d_U589.jpg?imageMogr2?imageMogr2?imageMogr2",
            "http://s0.mingxingyichu.cn/group5/M00/6B/1E/wKgBf1fOermAemBaAAI8p26-TxQ559.jpg?imageMogr2?imageMogr2?imageMogr2",
            "http://s6.mingxingyichu.cn/group6/M00/AE/1C/wKgBjFfNL3qAOcPrAAF-KXNTTgo759.jpg?imageMogr2?imageMogr2?imageMogr2",
            "http://s3.mingxingyichu.cn/group6/M00/AE/55/wKgBjVfOYOuAOkuFAAFS1Oz5VZc216.jpg?imageMogr2?imageMogr2?imageMogr2",
            "http://s6.mingxingyichu.cn/group6/M00/AE/49/wKgBjFfOXyqATirRAAMEd6BpW94137.jpg?imageMogr2?imageMogr2?imageMogr2"};

    //上部分Banner
    private Banner banner;
    //上部分，GridView及其适配器和数据源
    private CustomGridView fashionMiddleGridView;
    private List<FashionMiddleBean.ResponseBean.DataBean.ItemsBean> fashionMiddleDatas = new ArrayList<>();
    private FashionMiddleGridAdapter fashionMiddleGridAdapter;
    private TextView mAllTable;

    //参数
    private static int ga = 0;

    public static FashionRecommendFragment newInstance() {
        return new FashionRecommendFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fashion_fragment, container, false);

        //上部分，PullToRefreshListView其中只用一个item
        fashionListView = (PullToRefreshListView) view.findViewById(R.id.fashion_pull_list_view);
        fashionListViewAdapter = new FashionListViewAdapter(context);
        fashionListView.setAdapter(fashionListViewAdapter);
        //设置PullToRefreshListView的刷新模式,上拉刷新，下拉加载更多
        fashionListView.setMode(PullToRefreshBase.Mode.BOTH);


        //PullToRefreshListView的listview，用来加载上部分
        refreshableView = fashionListView.getRefreshableView();

        //上部视图
        View inflate = LayoutInflater.from(context).inflate(R.layout.fashion_foot_view, null);
        //上部视图的banner
        banner = (Banner) inflate.findViewById(R.id.fashion_list_item_banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner的默认动画,github查询方法
        banner.setBannerAnimation(Transformer.Stack);
        banner.setImages(images);
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(context, FashionTopDetailsActivity.class);
                intent.putExtra("id",id[position]);
                intent.putExtra("thread_id",id[position]+"");
                intent.putExtra("come","fashion");
                context.startActivity(intent);
            }
        });
        //上部视图的全部标签,和点击事件
        mAllTable = (TextView) inflate.findViewById(R.id.fashion_all_lable_tv);
        mAllTable.setOnClickListener(this);
        //上部分，gridview
        fashionMiddleGridView = (CustomGridView) inflate.findViewById(R.id.fashion_pull_middle_custom_grid_view);
        fashionMiddleGridAdapter = new FashionMiddleGridAdapter(fashionMiddleDatas, context);
        fashionMiddleGridView.setAdapter(fashionMiddleGridAdapter);

        //添加上部视图
        refreshableView.addHeaderView(inflate);

        requestDatas();
        initListener();
        return view;
    }

    private void initListener() {
        /**
         * 上部分gridview，热门推荐item的点击
         */
        fashionMiddleGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转到相应界面
                Intent intent = new Intent(context, FashionTableDetailsActivity.class);
                FashionMiddleBean.ResponseBean.DataBean.ItemsBean.ComponentBean.ActionBean actionBean = fashionMiddleDatas.get(i).getComponent().getAction();
                intent.putExtra("id", actionBean.getId());
                //用于判断进入FashionTableDetailsActivity界面的是哪个界面，用于 FashionTableDetailsActivity的返回
                intent.putExtra("come", "middle");
                intent.putExtra("title",actionBean.getTag());
                startActivity(intent);
            }
        });



        /**
         * 下拉刷新
         * 在下拉刷新中，时尚圈下部分第二个GridView的请求参数不变，只是刷新了数据
         * 标签部分参数发生了变化
         */
        fashionListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                ga++;
                requestDatas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    /**
     * 请求时尚圈上部分的数据,并刷新下部分GridView的适配器
     */
    private void requestDatas() {

        /**
         * 时尚圈上部分GridView的数据,并刷新上部分GridView的适配器,参数ga
         */
        HttpUtils.create().fashionMiddleGridDatas(ga).enqueue(new Callback<FashionMiddleBean>() {
            @Override
            public void onResponse(Call<FashionMiddleBean> call, Response<FashionMiddleBean> response) {
                if (fashionMiddleDatas != null) {
                    fashionMiddleDatas.clear();
                }
                fashionMiddleDatas.addAll(response.body().getResponse().getData().getItems());
                fashionMiddleGridAdapter.notifyDataSetChanged();

                fashionListView.onRefreshComplete();
            }


            @Override
            public void onFailure(Call<FashionMiddleBean> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //上部视图，GridView上面的全部标签的点击事件
            case R.id.fashion_all_lable_tv:
                Intent intent = new Intent(context, FashionMiddleTableActivity.class);
                startActivity(intent);
                break;
        }
    }
}
