package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidxx.yangjw.imageloader.ImageLoader;
import com.example.administrator.electronicproject.FashionFragment.bean.FansBean;
import com.example.administrator.electronicproject.FashionFragment.view.activity.ExpertPersonActivity;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sunbin on 2016/9/11.
 */
public class ExpertFansAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private List<FansBean.DataBean.ItemsBean> items;
    private boolean isAttr = false;

    public ExpertFansAdapter(Context context,List<FansBean.DataBean.ItemsBean> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
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
        FansHolder fansHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.expert_fans_item_layout,viewGroup,false);
            fansHolder = new FansHolder(view);
        }else {
            fansHolder = (FansHolder) view.getTag();
        }
        final FansBean.DataBean.ItemsBean.ComponentBean component = items.get(i).getComponent();
        fansHolder.nameTv.setText(component.getUserName());
        fansHolder.workTv.setText(component.getDescription());
        if (!component.getUserTypeName().equals("")){
            fansHolder.typeTv.setText(component.getUserTypeName());
        }else {
            fansHolder.typeTv.setVisibility(View.INVISIBLE);
        }
        fansHolder.headImg.setImageResource(R.drawable.icon_loading_data_null);
        if (component.getUserAvatar() != null){
            if (!component.getUserAvatar().equals("")){
                Picasso.with(context).load(component.getUserAvatar()).into(fansHolder.headImg);
//                ImageLoader.init(context).load(component.getUserAvatar(),fansHolder.headImg);
            }
        }
        fansHolder.attrTv.setText("+关注");
        fansHolder.attrTv.setTextColor(Color.RED);
        fansHolder.attrTv.setOnClickListener(this);
        fansHolder.headRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExpertPersonActivity.class);
                intent.putExtra("userId",component.getUserId());
                context.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.expert_fans_arrt_tv:
                if (!isAttr){
                    ((TextView)view).setText("已关");
                    ((TextView)view).setTextColor(Color.GRAY);
                }else {
                    ((TextView)view).setText("+关注");
                    ((TextView)view).setTextColor(Color.RED);
                }
                isAttr = !isAttr;
                break;
        }
    }

    class FansHolder{
        public FansHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }

        @BindView(R.id.expert_fans_arrt_tv)
        TextView attrTv;
        @BindView(R.id.expert_fans_head_rl)
        RelativeLayout headRl;
        @BindView(R.id.expert_fans_head_image)
        CircleImageView headImg;
        @BindView(R.id.expert_fans_name)
        TextView nameTv;
        @BindView(R.id.expert_fans_work)
        TextView workTv;
        @BindView(R.id.expert_user_type)
        TextView typeTv;
    }
}
