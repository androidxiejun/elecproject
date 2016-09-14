package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.example.administrator.electronicproject.FashionFragment.bean.ExpertPersonDetailsBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.view.CustomGridView;
import com.example.administrator.electronicproject.FashionFragment.view.FashionGridViewAdapter;
import com.example.administrator.electronicproject.FashionFragment.view.activity.FashionTopDetailsActivity;
import com.example.administrator.electronicproject.R;

import java.util.List;

/**
 * Created by sunbin on 2016/9/10.
 */
public class ExpertPersonAdapter extends BaseAdapter {

    private Context context;
    private List<ExpertPersonDetailsBean.ResponseBean.DataBean.ItemsBean> pullItems;
    private List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> secondItems;

    public ExpertPersonAdapter(Context context,
                               List<ExpertPersonDetailsBean.ResponseBean.DataBean.ItemsBean> pullItems
                                ,List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> secondItems){
        this.context = context;
        this.pullItems = pullItems;
        this.secondItems = secondItems;
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
        view = LayoutInflater.from(context).inflate(R.layout.expert_person_layout_item,viewGroup,false);
        CustomGridView gridView = (CustomGridView) view.findViewById(R.id.expert_person_item_grid);
        if (pullItems.size() != 0){
            ExpertPersonItemAdapter expertPersonItemAdapter = new ExpertPersonItemAdapter(context, pullItems);
            gridView.setAdapter(expertPersonItemAdapter);
        }
        if (secondItems.size() != 0){
            FashionGridViewAdapter fashionGridViewAdapter = new FashionGridViewAdapter(context, secondItems, null);
            gridView.setAdapter(fashionGridViewAdapter);
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = 0;
                if (pullItems.size() != 0){
                    id = pullItems.get(i).getComponent().getId();
                }
                if (secondItems.size() != 0){
                    id = secondItems.get(i).getComponent().getId();
                }
                Intent intent = new Intent(context, FashionTopDetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("come","person");
                intent.putExtra("thread_id","");
                context.startActivity(intent);
            }
        });
        return view;
    }


}
