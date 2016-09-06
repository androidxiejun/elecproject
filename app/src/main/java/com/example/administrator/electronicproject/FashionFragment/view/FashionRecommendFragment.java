package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionMiddleBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.R;
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
public class FashionRecommendFragment extends Fragment {

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
        //PullToRefreshListView的listview，用来加载下部分
        refreshableView = fashionListView.getRefreshableView();

        //底部视图
        View inflate = LayoutInflater.from(context).inflate(R.layout.fashion_foot_view, null);
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
        return view;
    }

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

                stringList.add("name");
                fashionListViewAdapter.notifyDataSetChanged();

                if (itemsBeanList != null){
                    itemsBeanList.clear();
                }
                itemsBeanList.addAll(response.body().getResponse().getData().getItems());//时尚圈下部分，获取网络数据源
                fashionGridViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<FashionBottonBean> call, Throwable t) {

            }
        });


        /**
         * 时尚圈下部分第一个GridView的数据,并刷新下部分GridView的适配器
         */
        HttpUtils.create().fashionMiddleGridDatas().enqueue(new Callback<FashionMiddleBean>() {
            @Override
            public void onResponse(Call<FashionMiddleBean> call, Response<FashionMiddleBean> response) {
                if (fashionMiddleDatas != null){
                    fashionMiddleDatas.clear();
                }
                fashionMiddleDatas.addAll(response.body().getResponse().getData().getItems());
                fashionMiddleGridAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<FashionMiddleBean> call, Throwable t) {

            }
        });
    }
}
