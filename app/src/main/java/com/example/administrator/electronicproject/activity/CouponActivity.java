package com.example.administrator.electronicproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/13.
 * 优惠券界面
 */
public class CouponActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.coupon_back_btn)
    Button backBtn;
    @BindView(R.id.coupon_convert)
    TextView convert;
    @BindView(R.id.coupon_list_view)
    PullToRefreshListView pullListView;
    @BindView(R.id.coupon_empty)
    RelativeLayout emptyView;
    private ListView refreshableView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_layout);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        convert.setOnClickListener(this);

        refreshableView = pullListView.getRefreshableView();
        refreshableView.setEmptyView(emptyView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.coupon_back_btn:
                finish();
                break;
            case R.id.coupon_convert:
                Intent intent = new Intent(this,CouponConvertActivity.class);
                startActivity(intent);
                break;
        }
    }
}
