package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.FashionFragment.view.activity.FashionTopDetailsActivity;
import com.example.administrator.electronicproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunbin on 2016/9/6.
 * 时尚圈主体部分，PullToRefreshListView中只含有一个CustomGridView，用来实现下拉刷新和viewpager效果
 */
public class FashionListViewAdapter extends BaseAdapter implements FashionGridViewAdapter.GridCallBack {

    private Context context;
    private List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> itemsBeanList = new ArrayList<>();
    private CustomGridView mCustonGridView;
    private FashionGridViewAdapter fashionGridViewAdapter;
    private static int last_item_id = 0;
    private static int flag = 0;
    private boolean addMore = false;


    public FashionListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.fashion_recommend_body, viewGroup, false);
        mCustonGridView = (CustomGridView) view.findViewById(R.id.fashion_pull_custom_grid_view);
        fashionGridViewAdapter = new FashionGridViewAdapter(context, itemsBeanList,this);
        mCustonGridView.setAdapter(fashionGridViewAdapter);

        requestMore();
        initListener();

        return view;
    }

    /**
     * 时尚圈主体GridView的数据,并刷新下部分GridView的适配器
     */
    private void requestMore() {
        HttpUtils.create().fashionBottomGridDatas(last_item_id, flag).enqueue(new Callback<FashionBottonBean>() {
            @Override
            public void onResponse(Call<FashionBottonBean> call, Response<FashionBottonBean> response) {
                FashionBottonBean.ResponseBean.DataBean dataBean = response.body().getResponse().getData();

                if (!addMore) {
                    if (itemsBeanList != null) {
                        itemsBeanList.clear();
                    }
                }
                //更新数据
                flag = dataBean.getFlag();
                last_item_id = dataBean.getLast_item_id();
                itemsBeanList.addAll(dataBean.getItems());//时尚圈主体部分，获取网络数据源

                //更新主体适配器
                fashionGridViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<FashionBottonBean> call, Throwable t) {

            }
        });

    }
        /**
         * 主体部分GridView的监听事件
         */

    private void initListener() {
        /**
         * 主体gridview的item的点击
         */
        mCustonGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, FashionTopDetailsActivity.class);
                intent.putExtra("id", itemsBeanList.get(i).getComponent().getId());
                intent.putExtra("thread_id", itemsBeanList.get(i).getComponent().getId() + "");
                intent.putExtra("come", "fashion");
                context.startActivity(intent);
            }
        });

    }


    @Override
    public void addMore() {
        requestMore();
    }
}
