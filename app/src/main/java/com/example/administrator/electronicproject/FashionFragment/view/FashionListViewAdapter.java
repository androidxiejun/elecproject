package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.electronicproject.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/6.
 * 时尚圈上部分，PullToRefreshListView中只含有一个banner，用来实现下拉刷新和viewpager效果
 */
public class FashionListViewAdapter extends BaseAdapter {

    //暂时使用的数据源
    private String[] images= new String[] {
            "http://ci.xiaohongshu.com/595dff6f-bad5-42f8-880c-9d58d64e88b2@r_640w_640h.webp",
            "http://ci.xiaohongshu.com/0071c59b-0f2d-461e-ace4-2643d88ef3d9@r_640w_640h.webp",
            "http://image.lanrenzhoumo.com/leo/img/20160810100309_273c841b233964e6b26fffbd5fb0474a.jpg",
            "http://image.lanrenzhoumo.com/leo/img/20160810100345_a37b7fccf945f2c8fa3a30e317968b5e.jpg",
            "http://image.lanrenzhoumo.com/leo/img/20160810100310_23ba61c23351583c295b63b494f0027c.jpg"};

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        FashionListHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fashion_top_list_item,viewGroup,false);
            holder = new FashionListHolder();
            holder.banner = (Banner) view.findViewById(R.id.fashion_list_item_banner);
            view.setTag(holder);
        }else {
            holder = (FashionListHolder) view.getTag();
        }
        holder.banner.setBannerStyle(Banner.SCROLL_INDICATOR_BOTTOM);
        holder.banner.setImages(images);
        return view;
    }

    class FashionListHolder{
        private Banner banner;
    }
}
