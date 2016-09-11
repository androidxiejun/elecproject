package com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailBean.BrandDetailBean;
import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailBean.BrandDetailTopBean;
import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailUtils.BrandDetailHttpUtils;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
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
    private ImageView topImage,topLogo;
    private TextView topText;
    private Button focuseBtn;
    private boolean isChecked=false;
    private List<BrandDetailBean.ResponseBean.DataBean.ItemsBean> items;
    private BrandDetailBean.ResponseBean.DataBean.ItemsBean.ComponentBean component;
    private BrandDetailTopBean.ResponseBean.DataBean.BusinessBean business;
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
        initView(view);
        handler=new Handler();
        recyclerView= (UltimateRecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        initListener();
//        recyclerView.enableDefaultSwipeRefresh(true);
//        recyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        recyclerView.setRefreshing(false);
//                    }
//                },2000);
//            }
//        });
        getInfo();
        return view;
    }

    /**
     * 点击Button时显示已关注，再次点击显示+关注
     */
    private void initListener() {
      focuseBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(!isChecked){
                  focuseBtn.setText("已关注");
                  isChecked=true;
              }else{
                  focuseBtn.setText("+关注");
                  isChecked=false;
              }
          }
      });
    }

    private void initView(View view) {
        topImage= (ImageView) view.findViewById(R.id.detail_brand_img_view);
        topText= (TextView) view.findViewById(R.id.detail_brand_text_view);
        topLogo= (ImageView) view.findViewById(R.id.detail_brand_logo);
        focuseBtn= (Button) view.findViewById(R.id.detail_focuse_btn);
    }

    /**
     * 进行网络数据加载，然后绑定适配器，刷新视图
     */
    private void getInfo(){
        //加载下部分视图数据
        BrandDetailHttpUtils.create().queryBean().enqueue(new Callback<BrandDetailBean>() {
            @Override
            public void onResponse(Call<BrandDetailBean> call, Response<BrandDetailBean> response) {
                items= response.body().getResponse().getData().getItems();
                adapter=new StringAdapter(items);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<BrandDetailBean> call, Throwable t) {

            }
        });
        //加载上部分视图数据
        BrandDetailHttpUtils.create().queryTopBean().enqueue(new Callback<BrandDetailTopBean>() {
            @Override
            public void onResponse(Call<BrandDetailTopBean> call, Response<BrandDetailTopBean> response) {
                business= response.body().getResponse().getData().getBusiness();
                if(!DetailBrandActivity.me.equals("xiejun")){
                    Picasso.with(context).load(DetailBrandActivity.imgUrl).into(topImage);
                }else{
                    Picasso.with(context).load(business.getBusiness_banner_url()).into(topImage);
                }
                topText.setText(business.getBusiness_brief());
                Picasso.with(context).load(business.getBusiness_image()).into(topLogo);
            }

            @Override
            public void onFailure(Call<BrandDetailTopBean> call, Throwable t) {

            }
        });
    }

    /**
     * 自定义UltimateRecyclerView的适配器
     */
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
        public void onBindViewHolder(final StringViewHolder holder, final int position) {
            component= items.get(position).getComponent();
            //一定要加这个判断  因为UltimateRecyclerView本身有加了头部和尾部  这个方法返回的是包括头部和尾部在内的
            if (position < getItemCount() && (customHeaderView != null ? position <= items.size() : position < items.size()) && (customHeaderView != null ? position > 0 : true)) {
                holder.imageView.setImageResource(R.mipmap.ic_launcher);
                holder.title.setText(component.getDescription());
                holder.currentPrice.setText("￥"+component.getPrice());
                holder.originPrice.setText("￥"+component.getOrigin_price());
                holder.originPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
                Picasso.with(context).load(component.getPicUrl()).into(holder.imageView);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position1 = holder.getPosition();
                        component=items.get(position1).getComponent();
                        Intent intent=new Intent(context, PurchaseDetails.class);
                        intent.putExtra("currentPrice",component.getPrice());
                        intent.putExtra("originPrice",component.getOrigin_price());
                        intent.putExtra("title",component.getDescription());
                        intent.putExtra("picUrl",component.getPicUrl());
                        startActivity(intent);
                    }
                });
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
