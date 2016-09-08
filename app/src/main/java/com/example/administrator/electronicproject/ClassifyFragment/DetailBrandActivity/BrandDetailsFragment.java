package com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailBean.BrandDetailBean;
import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailUtils.BrandDetailHttpUtils;
import com.example.administrator.electronicproject.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/8.
 */
public class BrandDetailsFragment extends Fragment{
    private Context context;
    private UltimateRecyclerView recyclerView;
    private StringAdapter adapter;
    private Handler handler;
    private List<BrandDetailBean.ResponseBean.DataBean.ItemsBean> items;
    private BrandDetailBean.ResponseBean.DataBean.ItemsBean.ComponentBean component;
    public static BrandDetailsFragment newInstance(){
        return new BrandDetailsFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_detail_brand,container,false);
       handler=new Handler();
        recyclerView= (UltimateRecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
//        adapter = new StringAdapter(items);
//        recyclerView.setAdapter(adapter);
        recyclerView.enableDefaultSwipeRefresh(true);
        recyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setRefreshing(false);
                    }
                },2000);
            }
        });
        getInfo();
        return view;
    }
    private void getInfo(){
        BrandDetailHttpUtils.create().queryBean().enqueue(new Callback<BrandDetailBean>() {
            @Override
            public void onResponse(Call<BrandDetailBean> call, Response<BrandDetailBean> response) {
                items= response.body().getResponse().getData().getItems();
//                adapter.notifyDataSetChanged();
                adapter=new StringAdapter(items);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<BrandDetailBean> call, Throwable t) {

            }
        });
    }
    public class StringViewHolder extends UltimateRecyclerviewViewHolder {
        public ImageView  imageView;
        public TextView  title,currentPrice,originPrice;
        public StringViewHolder(View itemView,boolean isItem) {
            super(itemView);
            if(isItem){
               imageView= (ImageView) itemView.findViewById(R.id.brand_detail_ultimate_img);
                title= (TextView) itemView.findViewById(R.id.brand_detail_ultimate_title);
                currentPrice= (TextView) itemView.findViewById(R.id.brand_detail_ultimate_currentprice);
                originPrice= (TextView) itemView.findViewById(R.id.brand_detail_ultimate_originprice);
            }
        }
    }
//    viewHolder.originPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
    class StringAdapter extends UltimateViewAdapter<StringViewHolder> {
        private List<BrandDetailBean.ResponseBean.DataBean.ItemsBean> items;

        public StringAdapter(List<BrandDetailBean.ResponseBean.DataBean.ItemsBean> items) {
            this.items = items;
        }
        @Override
        public StringViewHolder newFooterHolder(View view) {
            return null;
        }
        @Override
        public StringViewHolder newHeaderHolder(View view) {
            return null;
        }
        @Override
        public StringViewHolder onCreateViewHolder(ViewGroup parent) {
            View view  = LayoutInflater.from(context).inflate(R.layout.brand_detail_ultimatefresh_item,null);
            return new StringViewHolder(view,true);
        }
        @Override
        public int getAdapterItemCount() {
            return items==null?0:items.size();
            //这里返回的是你的item的个数  不包括头部和加载view
        }
        @Override
        public long generateHeaderId(int position) {
            return -1;
        }

        @Override
        public void onBindViewHolder(StringViewHolder holder, int position) {
            component= items.get(position).getComponent();
            //一定要加这个判断  因为UltimateRecyclerView本身有加了头部和尾部  这个方法返回的是包括头部和尾部在内的
            if (position < getItemCount() && (customHeaderView != null ? position <= items.size() : position < items.size()) && (customHeaderView != null ? position > 0 : true)) {
                holder.imageView.setImageResource(R.mipmap.ic_launcher);
                holder.title.setText(component.getDescription());
                holder.currentPrice.setText("￥"+component.getPrice());
                holder.originPrice.setText("￥"+component.getOrigin_price());
                holder.originPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
                Picasso.with(context).load(component.getPicUrl()).into(holder.imageView);
                //TODO
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            View view  = LayoutInflater.from(context).inflate(R.layout.brand_detail_ultimatefresh_item,null);
            return new StringViewHolder(view,true);
            //初始化item的头部布局  这里为了方便 就直接用StringViewHolder,实际使用可以使用不同于item的布局
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(customHeaderView!=null){
                position-=1;
            }
        }
    }
}
