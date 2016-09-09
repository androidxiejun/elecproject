package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
 */
public class FashionRecommendFragment extends Fragment implements View.OnClickListener{

    private Context context;

    //上部分，PullToRefreshListView及其适配器和数据源
    private PullToRefreshListView fashionListView;
    private List<String> stringList = new ArrayList<>();
    private FashionListViewAdapter fashionListViewAdapter;
    //PullToRefreshListView的listview，用来加载下部分
    private ListView refreshableView;

    //下部分，第二个GridView及其适配器和数据源
    private CustomGridView mCustonGridView;
    private FashionGridViewAdapter fashionGridViewAdapter;
    private List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> itemsBeanList = new ArrayList<>();
    //下部分，第一个GridView及其适配器和数据源
    private CustomGridView fashionMiddleGridView;
    private List<FashionMiddleBean.ResponseBean.DataBean.ItemsBean> fashionMiddleDatas = new ArrayList<>();
    private FashionMiddleGridAdapter fashionMiddleGridAdapter;
    private TextView mAllTable;

    //参数
    private int ga = 0;


    public static FashionRecommendFragment newInstance(){
        return new FashionRecommendFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fashion_fragment,container,false);

        //上部分，PullToRefreshListView其中只用一个item
        fashionListView = (PullToRefreshListView) view.findViewById(R.id.fashion_pull_list_view);
        fashionListViewAdapter = new FashionListViewAdapter(context,stringList);
        fashionListView.setAdapter(fashionListViewAdapter);
        //设置PullToRefreshListView的刷新模式,上拉刷新，下拉加载更多
        fashionListView.setMode(PullToRefreshBase.Mode.BOTH);

        //暂时存放最上边数据
        stringList.add("name");
        fashionListViewAdapter.notifyDataSetChanged();

        //PullToRefreshListView的listview，用来加载下部分
        refreshableView = fashionListView.getRefreshableView();

        //底部视图
        View inflate = LayoutInflater.from(context).inflate(R.layout.fashion_foot_view, null);
        //底部视图的全部标签,和点击事件
        mAllTable = (TextView) inflate.findViewById(R.id.fashion_all_lable_tv);
        mAllTable.setOnClickListener(this);
        //下部分，第一个gridview
        fashionMiddleGridView = (CustomGridView) inflate.findViewById(R.id.fashion_pull_middle_custom_grid_view);
        fashionMiddleGridAdapter = new FashionMiddleGridAdapter(fashionMiddleDatas,context);
        fashionMiddleGridView.setAdapter(fashionMiddleGridAdapter);
        //下部分，第二个gridview
        mCustonGridView = (CustomGridView) inflate.findViewById(R.id.fashion_pull_custom_grid_view);
        fashionGridViewAdapter = new FashionGridViewAdapter(context,itemsBeanList);
        mCustonGridView.setAdapter(fashionGridViewAdapter);

        //添加底部视图
        refreshableView.addFooterView(inflate);

        requestDatas();
        initListener();
        return view;
    }

    private void initListener() {
        /**
         * 下部分，第一个gridview，热门推荐item的点击
         */
        fashionMiddleGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转到相应界面
                Intent intent = new Intent(context, FashionTableDetailsActivity.class);
                int id = fashionMiddleDatas.get(i).getComponent().getAction().getId();
                intent.putExtra("id",id);
                //用于判断进入FashionTableDetailsActivity界面的是哪个界面，用于 FashionTableDetailsActivity的返回
                intent.putExtra("come","middle");
                startActivity(intent);
            }
        });

        /**
         * 下部分，第二个gridview的item的点击
         */
        mCustonGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context,FashionTopDetailsActivity.class);
                intent.putExtra("id",itemsBeanList.get(i).getComponent().getId());
                intent.putExtra("thread_id",itemsBeanList.get(i).getComponent().getId()+"");
                intent.putExtra("come","fashion");
                startActivity(intent);
            }
        });

        /**
         * 下拉刷新，上拉加载更多内容
         * 在下拉刷新中，时尚圈下部分第二个GridView的请求参数不变，只是刷新了数据
         * 标签部分参数发生了变化
         */
        fashionListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                ga ++;
                requestDatas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    fashionGridViewAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    fashionMiddleGridAdapter.notifyDataSetChanged();
                    break;
            }
            fashionListView.onRefreshComplete();
        }
    };

    /**
     * 请求时尚圈下部分的数据,并刷新下部分GridView的适配器
     */
    private void requestDatas(){
        /**
         * 时尚圈下部分第二个GridView的数据,并刷新下部分GridView的适配器
         */
        HttpUtils.create().fashionBottomGridDatas().enqueue(new Callback<FashionBottonBean>() {
            @Override
            public void onResponse(Call<FashionBottonBean> call, Response<FashionBottonBean> response) {


                if (itemsBeanList != null){
                    itemsBeanList.clear();
                }
                itemsBeanList.addAll(response.body().getResponse().getData().getItems());//时尚圈下部分，获取网络数据源
//                fashionGridViewAdapter.notifyDataSetChanged();

                Message message = mHandler.obtainMessage();
                message.what = 1;
                mHandler.sendMessage(message);

            }

            @Override
            public void onFailure(Call<FashionBottonBean> call, Throwable t) {

            }
        });


        /**
         * 时尚圈下部分第一个GridView的数据,并刷新下部分GridView的适配器,参数ga
         */
        HttpUtils.create().fashionMiddleGridDatas(ga).enqueue(new Callback<FashionMiddleBean>() {
            @Override
            public void onResponse(Call<FashionMiddleBean> call, Response<FashionMiddleBean> response) {
                if (fashionMiddleDatas != null){
                    fashionMiddleDatas.clear();
                }
                fashionMiddleDatas.addAll(response.body().getResponse().getData().getItems());
//                fashionMiddleGridAdapter.notifyDataSetChanged();

                Message message = mHandler.obtainMessage();
                message.what = 2;
                mHandler.sendMessage(message);
            }


            @Override
            public void onFailure(Call<FashionMiddleBean> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //底部视图，第一个GridView上面的全部标签的点击事件
            case R.id.fashion_all_lable_tv:
                Intent intent = new Intent(context, FashionMiddleTableActivity.class);
                startActivity(intent);
                break;
        }
    }
}
