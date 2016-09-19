package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.ExpertBean;
import com.example.administrator.electronicproject.FashionFragment.view.CustomGridView;
import com.example.administrator.electronicproject.FashionFragment.view.activity.ExpertPersonActivity;
import com.example.administrator.electronicproject.FashionFragment.view.activity.FashionTopDetailsActivity;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import attention.com.example.administrator.electronicproject.Attention;
import attention.com.example.administrator.electronicproject.AttentionUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sunbin on 2016/9/9.
 * 衣橱达人界面中listview的适配器
 */
public class ExpertAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private Context context;
    private List<ExpertBean.DataBean.ItemsBean> expertDatas;
    private boolean ifClick = false;
    private List<Attention> attentions;

    public ExpertAdapter(Context context, List<ExpertBean.DataBean.ItemsBean> expertDatas) {
        this.context = context;
        this.expertDatas = expertDatas;
        mInflater = LayoutInflater.from(context);
        attentions = AttentionUtils.getDao(context).loadAll();
    }

    @Override
    public int getCount() {
        return expertDatas == null ? 0 : expertDatas.size();
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
        ExpertHoler expertHoler = null;
        if (view == null) {
            view = mInflater.from(context).inflate(R.layout.expert_layout_item, viewGroup, false);
            expertHoler = new ExpertHoler(view);
        } else {
            expertHoler = (ExpertHoler) view.getTag();
        }
        final ExpertBean.DataBean.ItemsBean.ComponentBean componentBean = expertDatas.get(i).getComponent();
        final int userId = componentBean.getUserId();
        //头像
//        expertHoler.headView.setImageResource(R.drawable.icon_information_on);
        if (componentBean.getUserAvatar() != null) {
            Picasso.with(context).load(componentBean.getUserAvatar()).into(expertHoler.headView);
        }
        //名称和职业
        expertHoler.nameTv.setText(componentBean.getUserName());
        expertHoler.userTypeName.setText(componentBean.getUserTypeName());

        //图片，使用CustomGridView展示
        expertHoler.imageOne.setEmptyView(expertHoler.emptyIv);
        AnimationDrawable drawable = (AnimationDrawable) expertHoler.emptyIv.getDrawable();
        drawable.start();

        final List<ExpertBean.DataBean.ItemsBean.ComponentBean.PicsBean> picsBeen = componentBean.getPics();
        ExpertItemAdapter expertItemAdapter = new ExpertItemAdapter(context, picsBeen);
        expertHoler.imageOne.setAdapter(expertItemAdapter);
        //图片的点击事件
        expertHoler.imageOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = picsBeen.get(i).getComponent().getAction().getId();
                Intent intent = new Intent(context, FashionTopDetailsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("thread_id", "");
                intent.putExtra("come", "expert");
                context.startActivity(intent);
            }
        });

//        for (int j = 0; j < attentions.size(); j++) {
//            if (attentions.get(j).getUserId() == userId) {
//                expertHoler.attrTv.setText("已关");
//                expertHoler.attrTv.setTextColor(Color.GRAY);
//                break;
//            }
//        }

        //点击关注或者取消关注
        expertHoler.attrTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!ifClick) {
                    ((TextView) view).setText("已关");
                    ((TextView) view).setTextColor(Color.GRAY);
                    Attention attention = new Attention();
                    attention.setUserId(userId);
                    attention.setUserImage(componentBean.getUserAvatar());
                    attention.setUserName(componentBean.getUserName());
                    attention.setUserWork(componentBean.getUserTypeName());
                    AttentionUtils.getDao(context).insertOrReplace(attention);
                } else {
                    ((TextView) view).setText("+关注");
                    ((TextView) view).setTextColor(Color.RED);
                    for (int i = 0; i < attentions.size(); i++) {
                        if (attentions.get(i).getUserId() == userId) {
                            AttentionUtils.getDao(context).delete(attentions.get(i));
                        }
                    }
                }
                ifClick = !ifClick;
            }
        });


        //点击进入个人信息
        expertHoler.headRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExpertPersonActivity.class);
                intent.putExtra("userId", userId);
                context.startActivity(intent);
            }
        });

        return view;
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            //点击关注按钮
//            case R.id.expert_head_attention:
//                if (!ifClick){
//                    ((TextView)view).setText("已关");
//                    ((TextView)view).setTextColor(Color.GRAY);
//                }else {
//                    ((TextView)view).setText("+关注");
//                    ((TextView)view).setTextColor(Color.RED);
//                }
//                ifClick = !ifClick;
//                break;
//        }
//
//    }


    class ExpertHoler {

        public ExpertHoler(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
        }

        @BindView(R.id.expert_head_circl_iv)
        CircleImageView headView;
        @BindView(R.id.expert_head_name)
        TextView nameTv;
        @BindView(R.id.expert_head_date)
        TextView userTypeName;
        @BindView(R.id.expert_head_attention)
        TextView attrTv;
        @BindView(R.id.expert_image_one)
        CustomGridView imageOne;
        @BindView(R.id.expert_head_relative)
        RelativeLayout headRl;
        @BindView(R.id.expert_pull_empty)
        ImageView emptyIv;
    }
}
