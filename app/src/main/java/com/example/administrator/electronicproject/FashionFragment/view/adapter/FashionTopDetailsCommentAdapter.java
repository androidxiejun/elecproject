package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.RecommendTopDetailsCommentBean;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sunbin on 2016/9/7.
 */
public class FashionTopDetailsCommentAdapter extends BaseAdapter {

    private Context context;
    private List<RecommendTopDetailsCommentBean.DataBean.ItemsBean> commentDatas;

    public FashionTopDetailsCommentAdapter(Context context,List<RecommendTopDetailsCommentBean.DataBean.ItemsBean> commentDatas){
        this.context = context;
        this.commentDatas = commentDatas;
    }

    @Override
    public int getCount() {
        return commentDatas == null? 0 : commentDatas.size();
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
        CommentHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fashion_top_details_comment,viewGroup,false);
            holder = new CommentHolder(view);
        }else {
            holder = (CommentHolder) view.getTag();
        }
        RecommendTopDetailsCommentBean.DataBean.ItemsBean.ComponentBean componentBean = commentDatas.get(i).getComponent();
        holder.nameTv.setText(componentBean.getUserName());
        holder.dateTv.setText(componentBean.getPublishDate());
        holder.cotentTv.setText(componentBean.getContent());
        holder.imageView.setImageResource(R.drawable.ic_customer_service_girl);
        if (componentBean.getUserAvatar().length() > 10){
            Picasso.with(context).load(componentBean.getUserAvatar()).into(holder.imageView);
        }
        return view;
    }

    class CommentHolder{
        @BindView(R.id.recommend_top_details_head_comment)
        CircleImageView imageView;
        @BindView(R.id.recommend_top_details_comment_name)
        TextView nameTv;
        @BindView(R.id.recommend_top_details_comment_date)
        TextView dateTv;
        @BindView(R.id.recommend_top_details_comment_attention)
        TextView replyTv;
        @BindView(R.id.recommend_top_details_comment_content)
        TextView cotentTv;


        public CommentHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
