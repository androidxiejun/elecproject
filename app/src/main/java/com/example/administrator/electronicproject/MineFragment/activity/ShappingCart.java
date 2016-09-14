package com.example.administrator.electronicproject.MineFragment.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/12.
 * 购物车界面
 */
public class ShappingCart extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.shapping_cart_back_btn)
    Button backBtn;
    @BindView(R.id.shopping_cart_message)
    ImageView messageIv;
    @BindView(R.id.shopping_cart_editing)
    TextView editTv;
    @BindView(R.id.shopping_cart_expand_list)
    PullToRefreshExpandableListView expandableListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shapping_cart_layout);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        messageIv.setOnClickListener(this);
        editTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shapping_cart_back_btn://返回
                finish();
                break;
            case R.id.shopping_cart_message://消息
                break;
            case R.id.shopping_cart_editing://编辑
                break;
        }
    }
}
