package com.example.administrator.electronicproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.adapter.AddressAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

import addressdao.com.example.administrator.electronicproject.Address;
import addressdao.com.example.administrator.electronicproject.DBUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/13.
 * 收货地址
 */
public class AddressActivity extends AppCompatActivity implements View.OnClickListener,AddressAdapter.AddressCallBack{

    @BindView(R.id.address_back_btn)
    Button backBtn;
    @BindView(R.id.address_add)
    TextView convert;
    @BindView(R.id.address_list_view)
    PullToRefreshListView pullListView;
    @BindView(R.id.address_empty)
    RelativeLayout emptyView;
    private ListView refreshableView;
    private List<Address> addressesList = new ArrayList<>();
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_layout);
        ButterKnife.bind(this);


        initView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        addressesList.clear();
        addressesList.addAll(DBUtils.getDao(this).loadAll());
        addressAdapter.notifyDataSetChanged();
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        convert.setOnClickListener(this);

        refreshableView = pullListView.getRefreshableView();
        refreshableView.setEmptyView(emptyView);

        addressesList.addAll(DBUtils.getDao(this).loadAll());

        addressAdapter = new AddressAdapter(this,addressesList,this);
        pullListView.setAdapter(addressAdapter);

//        initListener();
    }

//    private void initListener() {
//        pullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                CheckBox box = (CheckBox) view.findViewById(R.id.user_address_image);
//                box.setChecked(true);
//            }
//        });
//    }

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

    /**
     * 适配器中的接口回调，点击返回我的界面,和删除地址信息
     */
    @Override
    public void returnMine(int position) {
        switch (position){
            case -1:
                finish();
                break;
            default:
                DBUtils.getDao(this).delete(addressesList.get(position));
                addressesList.remove(position);
                addressAdapter.notifyDataSetChanged();
                break;
        }
    }

}
