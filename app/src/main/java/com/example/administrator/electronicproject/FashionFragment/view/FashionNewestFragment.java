package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunbin on 2016/9/6.
 * 时尚圈“最新”的fragment,使用PullToRefreshGridView布局
 */
public class FashionNewestFragment extends Fragment {

    private Context context;
    //最新的gridview和数据源、适配器
    private PullToRefreshGridView mNewestGridView;
    private List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> newestGridDatas = new ArrayList<>();
    private FashionGridViewAdapter fashionNewestGridAdapter;
    private GridView refreshableView;//从PullToRefreshGridView获取的GridView，用于添加空视图
    private ImageView emptyView;

    public static FashionNewestFragment newInstance(){
        return new FashionNewestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        fashionNewestGridAdapter = new FashionGridViewAdapter(context,newestGridDatas,null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fashion_newest_layout, container, false);

        mNewestGridView = (PullToRefreshGridView) inflate.findViewById(R.id.fashion_newest_pull_grid_view);
        mNewestGridView.setAdapter(fashionNewestGridAdapter);
        //添加空视图
        refreshableView = mNewestGridView.getRefreshableView();
        emptyView = (ImageView) inflate.findViewById(R.id.fashion_newes_pull_empty_iv);
        refreshableView.setEmptyView(emptyView);
        //获得帧动画对象
        AnimationDrawable animationDrawable = (AnimationDrawable) emptyView.getDrawable();
        animationDrawable.start();

        requestDatas();
        initListener();
        return inflate;
    }

    private void initListener() {
        /**
         * 最新界面中，PullToRefreshGridView的item的点击事件
         */
        mNewestGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转到帖子详情界面
            }
        });
    }

    /**
     * 请求时尚圈最新的网络数据
     */
    private void requestDatas() {
        HttpUtils.create().fashionNewestGridDatas().enqueue(new Callback<FashionBottonBean>() {
            @Override
            public void onResponse(Call<FashionBottonBean> call, Response<FashionBottonBean> response) {
                if (newestGridDatas != null){
                    newestGridDatas.clear();
                }
                newestGridDatas.addAll(response.body().getResponse().getData().getItems());
                fashionNewestGridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FashionBottonBean> call, Throwable t) {

            }
        });
    }


}
