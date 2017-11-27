package com.example.administrator.electronicproject.MineFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.MineFragment.bean.VIPBean;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/13.
 */
public class VIPAdapter extends BaseAdapter {

    private Context context;
    private List<VIPBean.DataBean.SkusBean> listDatas;

    public VIPAdapter(Context context,List<VIPBean.DataBean.SkusBean> listDatas){
        this.context = context;
        this.listDatas = listDatas;
    }

    @Override
    public int getCount() {
        return listDatas == null ? 0 : listDatas.size();
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
        VipHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.vip_person_item,viewGroup,false);
            holder = new VipHolder(view);
        }else {
            holder = (VipHolder) view.getTag();
        }
        final VIPBean.DataBean.SkusBean skusBean = listDatas.get(i);
        holder.level.setText("v0");
        if (skusBean.getTitle()!= null){
            holder.title.setText(skusBean.getTitle());
        }
        if (!skusBean.getPicUrl().equals("") && skusBean.getPicUrl() != null){
            Picasso.with(context).load(skusBean.getPicUrl()).into(holder.imageView);
        }
        if (skusBean.getOriginPrice() != null){
            holder.price.setText("￥ "+skusBean.getOriginPrice());
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PurchaseDetails.class);
//                intent.putExtra("source_id",skusBean.getAction().getSourceId());
                intent.putExtra("source_id","2429284");
                context.startActivity(intent);
            }
        });
        return view;
    }

    class VipHolder{
        public VipHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }

        @BindView(R.id.vip_person_item_image)
        ImageView imageView;
        @BindView(R.id.vip_person_item_title)
        TextView title;
        @BindView(R.id.vip_person_item_level)
        TextView level;
        @BindView(R.id.vip_person_item_price)
        TextView price;
    }
}
