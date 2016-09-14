package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.electronicproject.FashionFragment.bean.RecommendTopDetailsCommentBean;
import com.example.administrator.electronicproject.FashionFragment.view.CustomGridView;
import com.example.administrator.electronicproject.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sunbin on 2016/9/11.
 */
public class RecommendAdapter extends BaseAdapter {

    private Context context;
    private List<RecommendTopDetailsCommentBean.DataBean.ItemsBean> commentDatas;

    public RecommendAdapter(Context context,List<RecommendTopDetailsCommentBean.DataBean.ItemsBean> commentDatas){
        this.context = context;
        this.commentDatas = commentDatas;
    }


    @Override
    public int getCount() {
        return 1;
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
        view = LayoutInflater.from(context).inflate(R.layout.recommend_item,viewGroup,false);
        CustomGridView customGrid = (CustomGridView) view.findViewById(R.id.recommend_comment_grid_view);
        FashionTopDetailsCommentAdapter adapter = new FashionTopDetailsCommentAdapter(context, commentDatas);
        customGrid.setAdapter(adapter);
        return view;
    }
}
