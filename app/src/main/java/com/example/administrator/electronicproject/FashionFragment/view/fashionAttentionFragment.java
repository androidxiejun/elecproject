package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.view.activity.ExpertActivity;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.AttentionAdapter;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.adapter.AddressAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import attention.com.example.administrator.electronicproject.Attention;
import attention.com.example.administrator.electronicproject.AttentionUtils;

/**
 * Created by sunbin on 2016/9/9.
 * 衣橱达人的关注
 */
public class FashionAttentionFragment extends Fragment implements AttentionAdapter.AttentionCallBack{

    private Context context;
    private PullToRefreshGridView mAttentionGridView;
    private Button mBtn;
    private PullToRefreshListView pullList;
    private ListView refreshableView;
    private RelativeLayout empty;
    private List<Attention> attentions;
    private AttentionAdapter attentionAdapter;
    private View footView;
    private TextView footTv;

    public static FashionAttentionFragment newInstance(){
        return new FashionAttentionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fashion_attention_first, container, false);

        pullList = (PullToRefreshListView) view.findViewById(R.id.fashion_attent_pull_list_view);
        refreshableView = pullList.getRefreshableView();

        empty = (RelativeLayout) view.findViewById(R.id.fashion_attent_empty);
        refreshableView.setEmptyView(empty);
        footView = LayoutInflater.from(context).inflate(R.layout.attention_foot_layout,null);
        refreshableView.addFooterView(footView);
        footTv = (TextView) footView.findViewById(R.id.attention_foot_tv);

        attentions = AttentionUtils.getDao(context).loadAll();
        attentionAdapter = new AttentionAdapter(context,attentions,this);
        pullList.setAdapter(attentionAdapter);

        mBtn = (Button) view.findViewById(R.id.fashion_attention_welcome_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExpertActivity.class);
                startActivity(intent);
            }
        });
        footTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExpertActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void notifyAdapter() {
        attentions.clear();
        attentions.addAll(AttentionUtils.getDao(context).loadAll());
        attentionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        attentions.clear();
        attentions.addAll(AttentionUtils.getDao(context).loadAll());
        attentionAdapter.notifyDataSetChanged();
    }
}
