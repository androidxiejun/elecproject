package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.TableBean;
import com.example.administrator.electronicproject.R;
import com.hhl.library.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunbin on 2016/9/8.
 */
public class TagAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<TableBean.ResponseBean.DataBean.ItemsBean.ListBean> listBeen;

    public TagAdapter(Context context) {
        this.mContext = context;
        listBeen = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.fashion_middle_table_btn, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        textView.setText("#"+listBeen.get(position).getTag());
        return view;
    }

    public void onlyAddAll(List<TableBean.ResponseBean.DataBean.ItemsBean.ListBean> datas) {
        listBeen.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<TableBean.ResponseBean.DataBean.ItemsBean.ListBean> datas) {
        listBeen.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
