package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.TableBean;
import com.example.administrator.electronicproject.FashionFragment.view.activity.FashionTableDetailsActivity;
import com.example.administrator.electronicproject.FashionFragment.view.customview.FlowLayout;
import com.example.administrator.electronicproject.R;
import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagClickListener;

import java.util.List;

/**
 * Created by sunbin on 2016/9/8.
 * 时尚圈中，全部标签界面中listview的适配器
 */
public class FashionMiddleTableAdapter extends BaseAdapter {

    private List<TableBean.ResponseBean.DataBean.ItemsBean> tableListDatas;
    private Context context;
    private  LayoutInflater layoutInflater;
    private TagAdapter tagAdapter;

    public FashionMiddleTableAdapter(List<TableBean.ResponseBean.DataBean.ItemsBean> tableListDatas,Context context){
        this.tableListDatas = tableListDatas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tableListDatas == null ? 0 : tableListDatas.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        FashionTableHolder tableHolder = null;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.fashion_middle_table_item, viewGroup, false);
            tableHolder = new FashionTableHolder();
            tableHolder.textView = (TextView) view.findViewById(R.id.fashion_table_title_tv);
            tableHolder.flowLayout = (FlowTagLayout) view.findViewById(R.id.fashion_table_flow_layout);
            view.setTag(tableHolder);
        } else {
            tableHolder = (FashionTableHolder) view.getTag();
        }
        tableHolder.textView.setText(tableListDatas.get(position).getCategory());
        final List<TableBean.ResponseBean.DataBean.ItemsBean.ListBean> listBeen = tableListDatas.get(position).getList();
        //标签流式布局的适配器
        tagAdapter = new com.example.administrator.electronicproject.FashionFragment.view.adapter.TagAdapter(context);
        tableHolder.flowLayout.setAdapter(tagAdapter);
        tagAdapter.onlyAddAll(listBeen);

        //标签的点击事件
        tableHolder.flowLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                Intent intent = new Intent(context, FashionTableDetailsActivity.class);
                //传递参数，listBeen的id
                intent.putExtra("id",listBeen.get(position).getId());
                //用于判断进入FashionTableDetailsActivity界面的是哪个界面，用于 FashionTableDetailsActivity的返回
                intent.putExtra("come","table");
                context.startActivity(intent);
            }
        });

        return view;
    }

    class FashionTableHolder{
        TextView textView;
        FlowTagLayout flowLayout;
    }
}
