package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.imageloader.ImageLoader;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionMiddleBean;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sunbin on 2016/9/6.
 */
public class FashionMiddleGridAdapter extends BaseAdapter {

    private List<FashionMiddleBean.ResponseBean.DataBean.ItemsBean> fashionMiddleDatas;
    private Context context;

    public FashionMiddleGridAdapter(List<FashionMiddleBean.ResponseBean.DataBean.ItemsBean> fashionMiddleDatas,Context context){
        this.fashionMiddleDatas = fashionMiddleDatas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return fashionMiddleDatas == null ? 0 : fashionMiddleDatas.size();
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
        FashionMiddleHolder middleHolder = null;

        if (null == view){
            view = LayoutInflater.from(context).inflate(R.layout.fashion_middle_grid_item,viewGroup,false);
            middleHolder = new FashionMiddleHolder();
            middleHolder.imageView = (ImageView) view.findViewById(R.id.fashion_middle_item_iv);
            middleHolder.textView = (TextView) view.findViewById(R.id.fashion_middle_item_txt);
            view.setTag(middleHolder);
        }else {
            middleHolder = (FashionMiddleHolder) view.getTag();
        }
        FashionMiddleBean.ResponseBean.DataBean.ItemsBean.ComponentBean component = fashionMiddleDatas.get(i).getComponent();
        middleHolder.textView.setText(component.getTitle());
        Picasso.with(context).load(component.getPicUrl()).into(middleHolder.imageView);
//        ImageLoader.init(context).load(component.getPicUrl(),middleHolder.imageView);
        return view;
    }

    class FashionMiddleHolder{
        private ImageView imageView;
        private TextView textView;
    }
}
