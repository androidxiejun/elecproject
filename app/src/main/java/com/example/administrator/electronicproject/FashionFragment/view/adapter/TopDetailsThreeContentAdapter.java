package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.RecommendDeatilsBean;
import com.example.administrator.electronicproject.R;

import java.util.List;

/**
 * Created by sunbin on 2016/9/10.
 */
public class TopDetailsThreeContentAdapter extends BaseAdapter {

    private Context context;
    private List<RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean.ComponentBean.DescriptionBean> description;

    public TopDetailsThreeContentAdapter(Context context,List<RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean.ComponentBean.DescriptionBean> description){
        this.context = context;
        this.description = description;
    }

    @Override
    public int getCount() {
        return description == null ? 0 : description.size();
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
        ThreeContentHolder contentHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fashion_recommend_top_details_item_one,viewGroup,false);
            contentHolder = new ThreeContentHolder();
            contentHolder.textView = (TextView) view.findViewById(R.id.recommend_top_details_title_item);
            view.setTag(contentHolder);
        }else {
            contentHolder = (ThreeContentHolder) view.getTag();
        }
        String text = description.get(i).getComponent().getText();
        if (!text.equals("")){
            contentHolder.textView.setText(text);
        }
        return view;
    }

    class ThreeContentHolder{
        private TextView textView;
    }
}
