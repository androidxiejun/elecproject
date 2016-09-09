package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.electronicproject.FashionFragment.view.activity.FashionTopDetailsActivity;
import com.example.administrator.electronicproject.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/6.
 * 时尚圈上部分，PullToRefreshListView中只含有一个banner，用来实现下拉刷新和viewpager效果
 */
public class FashionListViewAdapter extends BaseAdapter {

    //暂时使用的数据源
    private int[] id = {469139,469172,469162,469105,468584,469114};
    private String[] images= new String[] {
            "http://s3.mingxingyichu.cn/group6/M00/AE/2F/wKgBjVfNQEyASrljAALE86r9d_U589.jpg?imageMogr2?imageMogr2?imageMogr2",
            "http://s0.mingxingyichu.cn/group5/M00/6B/1E/wKgBf1fOermAemBaAAI8p26-TxQ559.jpg?imageMogr2?imageMogr2?imageMogr2",
            "http://s6.mingxingyichu.cn/group6/M00/AE/1C/wKgBjFfNL3qAOcPrAAF-KXNTTgo759.jpg?imageMogr2?imageMogr2?imageMogr2",
            "http://s3.mingxingyichu.cn/group6/M00/AE/55/wKgBjVfOYOuAOkuFAAFS1Oz5VZc216.jpg?imageMogr2?imageMogr2?imageMogr2",
            "http://s6.mingxingyichu.cn/group6/M00/AE/49/wKgBjFfOXyqATirRAAMEd6BpW94137.jpg?imageMogr2?imageMogr2?imageMogr2"};

    private Context context;
    private List<String> stringList;
    public FashionListViewAdapter(Context context,List<String> stringList){
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList == null ? 0 : stringList.size();
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
        FashionListHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fashion_top_list_item,viewGroup,false);
            holder = new FashionListHolder();
            holder.banner = (Banner) view.findViewById(R.id.fashion_list_item_banner);
            view.setTag(holder);
        }else {
            holder = (FashionListHolder) view.getTag();
        }
        holder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        holder.banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner的默认动画,github查询方法
//        holder.banner.setBannerAnimation(Transformer.Stack);
        holder.banner.setBannerAnimation(Transformer.Accordion);
        holder.banner.setImages(images);
        holder.banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(context, FashionTopDetailsActivity.class);
                intent.putExtra("id",id[position]);
                intent.putExtra("thread_id",id[position]+"");
                intent.putExtra("come","fashion");
                context.startActivity(intent);
            }
        });
        return view;
    }

    class FashionListHolder{
        private Banner banner;
    }
}
