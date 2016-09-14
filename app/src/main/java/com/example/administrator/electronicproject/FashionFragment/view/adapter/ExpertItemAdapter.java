package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.electronicproject.FashionFragment.bean.ExpertBean;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sunbin on 2016/9/10.
 */
public class ExpertItemAdapter extends BaseAdapter {

    private Context context;
    private List<ExpertBean.DataBean.ItemsBean.ComponentBean.PicsBean> picsBeen;

    public ExpertItemAdapter(Context context,List<ExpertBean.DataBean.ItemsBean.ComponentBean.PicsBean> picsBeen){
        this.context = context;
        this.picsBeen = picsBeen;
    }

    @Override
    public int getCount() {
        return picsBeen == null ? 0 : picsBeen.size();
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
        ViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.expert_layout_item_item,viewGroup,false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.expert_image_three);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        String picUrl = picsBeen.get(i).getComponent().getPicUrl();
        if (!picUrl.equals("")){
            Picasso.with(context).load(picUrl).into(holder.imageView);
        }
        return view;
    }

    class ViewHolder{
        ImageView imageView;
    }
}
