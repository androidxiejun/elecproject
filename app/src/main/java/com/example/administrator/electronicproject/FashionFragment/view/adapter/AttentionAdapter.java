package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidxx.yangjw.imageloader.ImageLoader;
import com.example.administrator.electronicproject.FashionFragment.view.activity.ExpertPersonActivity;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import attention.com.example.administrator.electronicproject.Attention;
import attention.com.example.administrator.electronicproject.AttentionUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sunbin on 2016/9/18.
 */
public class AttentionAdapter extends BaseAdapter {

    private Context context;
    private List<Attention> attentions;
    private AttentionCallBack callBack;

    public AttentionAdapter(Context context,List<Attention> attentions,AttentionCallBack callBack){
        this.context = context;
        this.attentions = attentions;
        this.callBack = callBack;
    }

    public interface AttentionCallBack{
        void notifyAdapter();
    }

    @Override
    public int getCount() {
        return attentions == null? 0 : attentions.size();
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
        AttentionHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.attention_layout,viewGroup,false);
            holder = new AttentionHolder(view);
        }else {
            holder = (AttentionHolder) view.getTag();
        }
        final Attention attention = attentions.get(i);
        Picasso.with(context).load(attention.getUserImage()).into(holder.headImage);
//        ImageLoader.init(context).load(attention.getUserImage(),holder.headImage);
        holder.name.setText(attention.getUserName());
        holder.work.setText(attention.getUserWork());
        holder.attent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttentionUtils.getDao(context).delete(attention);
                callBack.notifyAdapter();
            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExpertPersonActivity.class);
                intent.putExtra("userId",attention.getUserId());
                context.startActivity(intent);
            }
        });
        return view;
    }

    class AttentionHolder{
        public AttentionHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.attention_head_attention)
        TextView attent;
        @BindView(R.id.attention_head_relative)
        RelativeLayout relativeLayout;
        @BindView(R.id.attention_head_circl_iv)
        CircleImageView headImage;
        @BindView(R.id.attention_head_name)
        TextView name;
        @BindView(R.id.attention_head_date)
        TextView work;
    }
}
