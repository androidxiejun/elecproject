package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.imageloader.ImageLoader;
import com.example.administrator.electronicproject.FashionFragment.bean.ExpertPersonDetailsBean;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/10.
 */
public class ExpertPersonItemAdapter extends BaseAdapter {

    private Context context;
    private List<ExpertPersonDetailsBean.ResponseBean.DataBean.ItemsBean> pullItems;

    public ExpertPersonItemAdapter(Context context,List<ExpertPersonDetailsBean.ResponseBean.DataBean.ItemsBean> pullItems){
        this.context = context;
        this.pullItems = pullItems;
    }

    @Override
    public int getCount() {
        return pullItems == null ? 0 : pullItems.size();
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
        ItemHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.expert_item,viewGroup,false);
            holder = new ItemHolder(view);
        }else {
            holder = (ItemHolder) view.getTag();
        }
        ExpertPersonDetailsBean.ResponseBean.DataBean.ItemsBean.ComponentBean componentBean = pullItems.get(i).getComponent();
        String pics = componentBean.getPics();
        if (!pics.equals("")){
            Picasso.with(context).load(pics).into(holder.imageView);
        }
        holder.textView.setText(componentBean.getContent());
        return view;
    }

    class ItemHolder{
        public ItemHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }

        @BindView(R.id.expert_person_item_iv)
        ImageView imageView;
        @BindView(R.id.expert_person_item_tv)
        TextView textView;
    }
}
