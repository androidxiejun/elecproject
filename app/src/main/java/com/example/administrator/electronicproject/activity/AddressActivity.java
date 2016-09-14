package com.example.administrator.electronicproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.adapter.AddressAdapter;
import com.example.administrator.electronicproject.adapter.bean.UserAddress;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/13.
 * 收货地址
 */
public class AddressActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.address_back_btn)
    Button backBtn;
    @BindView(R.id.address_add)
    TextView convert;
    @BindView(R.id.address_list_view)
    PullToRefreshListView pullListView;
    @BindView(R.id.address_empty)
    RelativeLayout emptyView;
    private ListView refreshableView;
    private List<UserAddress> addressesList = new ArrayList<>();
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_layout);
        ButterKnife.bind(this);


        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        convert.setOnClickListener(this);

        refreshableView = pullListView.getRefreshableView();
        refreshableView.setEmptyView(emptyView);

        addressAdapter = new AddressAdapter(this,addressesList);
        pullListView.setAdapter(addressAdapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.address_back_btn://返回
                finish();
                break;
            case R.id.address_add://新增
                Intent add = new Intent(this,AddAddressActivity.class);
                startActivity(add);
                break;
        }
    }
}
